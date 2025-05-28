package com.example.booksliveapp.DB;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.booksliveapp.activitys.NotifiActivity;
import com.example.booksliveapp.activitys.PrincipalActivity;
import com.example.booksliveapp.modelo.Liburua;
import com.example.booksliveapp.modelo.Notifi;
import com.example.booksliveapp.modelo.User;
import com.example.booksliveapp.variables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class mysql {
    private static final String URL = "jdbc:mysql://192.168.1.134:3307/db_bookslive";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void ErabiltzaileaKonprobatu(final String erabiltzailea, final String pasahitza, final Activity activity, final Callback callback) {
        // Crear un hilo secundario
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean exists = false;
                String sql = "SELECT * FROM user WHERE erabiltzailea = ? AND pasahitza = ?";
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, erabiltzailea);
                    preparedStatement.setString(2, pasahitza);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        User user = new User();
                        user.setEmail(resultSet.getString("email"));
                        user.setErabiltzailea(resultSet.getString("erabiltzailea"));
                        user.setPasahitza(resultSet.getString("pasahitza"));
                        user.setIzenaAbizena(resultSet.getString("izena_abizena"));
                        user.setHelbidea(resultSet.getString("helbidea"));
                        user.setJaiotzaData(resultSet.getDate("jaiotza_data"));
                        user.setUserId(resultSet.getInt("user_id"));

                        if (user != null) {
                            exists = true;
                            variables.LoginUser = user; // Guardamos el usuario en la variable estática
                        }
                        Log.d("MYSQL", "Erabiltzailea aurkitu da: " + user.getErabiltzailea());
                    }
                } catch (SQLException e) {
                    Log.e("MYSQL", "Errorea: " + e.getMessage(), e);
                }

                final boolean finalExists = exists;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResult(finalExists);
                    }
                });
            }
        }).start(); // Inicia el hilo
    }

    // Interfaz de Callback para devolver el resultado
    public interface Callback {
        void onResult(boolean exists);
    }

    public static void ErabiltzaileaSortu(String izenaAbizena, String erabiltzailea, String email, String pasahitza, String helbidea, Date jaiotzaData, final Activity activity, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean exists = false;
                String sql = "SELECT * FROM user WHERE erabiltzailea = ?";

                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, erabiltzailea);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Si hay algún resultado, el usuario ya existe
                    if (resultSet.next()) {
                        exists = true;
                    } else {
                        // Si no existe, lo insertamos en la base de datos
                        String insertSql = "INSERT INTO user (izena_abizena, email, pasahitza, erabiltzailea, jaiotza_data, helbidea) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                            insertStmt.setString(1, izenaAbizena);
                            insertStmt.setString(2, email);
                            insertStmt.setString(3, pasahitza);
                            insertStmt.setString(4, erabiltzailea);
                            insertStmt.setDate(5, new java.sql.Date(jaiotzaData.getTime()));
                            insertStmt.setString(6, helbidea);

                            int rowsInserted = insertStmt.executeUpdate();
                            if (rowsInserted > 0) {
                                Log.d("MYSQL", "Erabiltzailea sortu da: " + erabiltzailea);
                            }
                        }
                    }
                } catch (SQLException e) {
                    Log.e("MYSQL", "Errorea: " + e.getMessage(), e);
                }

                final boolean finalExists = exists;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResult(finalExists); // Si `true`, ya existía; si `false`, se ha insertado nuevo
                    }
                });
            }
        }).start();
    }

    public static void LiburuaSortu(String liburuId, String tituloa, String idazlea, String generoa, double prezioa, String egoera, int userId, final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql = "INSERT INTO liburua (liburu_id, tituloa, idazlea, generoa, prezioa, egoera, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, liburuId);
                    preparedStatement.setString(2, tituloa);
                    preparedStatement.setString(3, idazlea);
                    preparedStatement.setString(4, generoa);
                    preparedStatement.setDouble(5, prezioa);
                    preparedStatement.setString(6, egoera);
                    preparedStatement.setInt(7, userId);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        Log.d("MYSQL", "Liburua sortu da: " + tituloa);
                    }
                } catch (SQLException e) {
                    Log.e("MYSQL", "Errorea: " + e.getMessage(), e);
                }
            }
        }).start();
    }

    public static void  LiburuakAtera(final Activity activity, final PrincipalActivity.LiburuaCallback callback) {
        // Crear un hilo secundario
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Liburua> liburuak = new ArrayList<>();

                String sql = "SELECT * FROM liburua";
                String sql2 = "SELECT * from user where user_id = ?";
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Liburua liburu = new Liburua();
                        liburu.setLiburuId(resultSet.getInt("liburu_id"));
                        liburu.setTituloa(resultSet.getString("tituloa"));
                        liburu.setIdazlea(resultSet.getString("idazlea"));
                        liburu.setGeneroa(resultSet.getString("generoa"));
                        liburu.setEgoera(resultSet.getString("egoera"));
                        liburu.setPrezioa(resultSet.getDouble("prezioa"));
                        liburu.setIrudia(resultSet.getString("imagen_android"));

                        int userId = resultSet.getInt("user_id");

                        // Preparar y ejecutar la consulta para el usuario
                        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                        preparedStatement2.setInt(1, userId);

                        ResultSet resultSet2 = preparedStatement2.executeQuery();

                        User user = null;
                        if (resultSet2.next()) {
                            user = new User();
                            user.setUserId(resultSet2.getInt("user_id"));
                            user.setIzenaAbizena(resultSet2.getString("izena_abizena"));
                            user.setEmail(resultSet2.getString("email"));
                            user.setPasahitza(resultSet2.getString("pasahitza"));
                            user.setErabiltzailea(resultSet2.getString("erabiltzailea"));
                            user.setJaiotzaData(resultSet2.getDate("jaiotza_data"));
                            user.setHelbidea(resultSet2.getString("helbidea"));
                        }

                        liburu.setUser(user);
                        liburuak.add(liburu);

                        Log.d("MYSQL", "Erabiltzailea aurkitu da: " + (user != null ? user.getErabiltzailea() : "null"));
                    }
                } catch (SQLException e) {
                    Log.e("MYSQL", "Errorea: " + e.getMessage(), e);
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onLiburuaLoaded(liburuak); // Aquí se pasa la lista de libros al callback
                    }
                });
            }
        }).start(); // Inicia el hilo
    }

    public static void NotifikazioakAtera(final Activity activity, final NotifiActivity.NotifikazioCallback callback) {
        new Thread(() -> {
            ArrayList<Notifi> notifikazioLista = new ArrayList<>();

            String sql = "SELECT * FROM NOTIFIKAZIOA";
            String sqlLiburua = "SELECT * FROM LIBURUA WHERE liburu_id = ?";
            String sqlUser = "SELECT * FROM USER WHERE user_id = ?";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Notifi notifi = new Notifi();
                    notifi.setId(rs.getInt("id"));
                    notifi.setEgoera(rs.getString("egoera"));
                    notifi.setMezua(rs.getString("mezua"));
                    notifi.setData(rs.getString("data"));

                    int liburuId = rs.getInt("liburu_id");
                    int erosleId = rs.getInt("erosketa_egilea_id");
                    int saltzaileId = rs.getInt("saltzailea_id");

                    // Liburua lortu
                    PreparedStatement psLiburua = conn.prepareStatement(sqlLiburua);
                    psLiburua.setInt(1, liburuId);
                    ResultSet rsLib = psLiburua.executeQuery();
                    if (rsLib.next()) {
                        Liburua liburu = new Liburua();
                        liburu.setLiburuId(rsLib.getInt("liburu_id"));
                        liburu.setTituloa(rsLib.getString("tituloa"));
                        liburu.setIdazlea(rsLib.getString("idazlea"));
                        liburu.setGeneroa(rsLib.getString("generoa"));
                        liburu.setPrezioa(rsLib.getDouble("prezioa"));
                        liburu.setEgoera(rsLib.getString("egoera"));
                        liburu.setIrudia(rsLib.getString("imagen_android"));
                        notifi.setLiburu(liburu);
                    }

                    // Eroslea lortu
                    PreparedStatement psErosle = conn.prepareStatement(sqlUser);
                    psErosle.setInt(1, erosleId);
                    ResultSet rsErosle = psErosle.executeQuery();
                    if (rsErosle.next()) {
                        User erosle = new User();
                        erosle.setUserId(rsErosle.getInt("user_id"));
                        erosle.setIzenaAbizena(rsErosle.getString("izena_abizena"));
                        notifi.setErosketaEgiailea(erosle);
                    }

                    // Saltzailea lortu
                    PreparedStatement psSaltzaile = conn.prepareStatement(sqlUser);
                    psSaltzaile.setInt(1, saltzaileId);
                    ResultSet rsSaltzaile = psSaltzaile.executeQuery();
                    if (rsSaltzaile.next()) {
                        User saltzaile = new User();
                        saltzaile.setUserId(rsSaltzaile.getInt("user_id"));
                        saltzaile.setIzenaAbizena(rsSaltzaile.getString("izena_abizena"));
                        notifi.setSaltzailea(saltzaile);
                    }

                    notifikazioLista.add(notifi);
                }

            } catch (SQLException e) {
                Log.e("MYSQL", "Errorea notifikazioak ateratzean: " + e.getMessage(), e);
            }

            activity.runOnUiThread(() -> {
                callback.onNotifikazioakLoaded(notifikazioLista);

            });

        }).start();
    }


    public static void NotifikazioaSortu(int liburuId, int erosketaEgiaileaId, int saltzaileaId, String titulo,final Activity activity, final Callback callback) {
        new Thread(() -> {
            boolean inserted = false;
            String insertSql = "INSERT INTO NOTIFIKAZIOA (liburu_id, erosketa_egilea_id, saltzailea_id, egoera, mezua) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {

                insertStmt.setInt(1, liburuId);
                insertStmt.setInt(2, erosketaEgiaileaId);
                insertStmt.setInt(3, saltzaileaId);
                insertStmt.setString(4, "Zain"); // Ej: "Zain", "Onartua", "Ezeztatuta"
                insertStmt.setString(5, variables.LoginUser.getErabiltzailea() + " " + titulo + " erosi nahi du"); // Mensaje de notificación

                int rowsInserted = insertStmt.executeUpdate();
                inserted = rowsInserted > 0;

                if (inserted) {
                    Log.d("MYSQL", "Notifikazioa bidali da!");
                }

            } catch (SQLException e) {
                Log.e("MYSQL", "Errorea Notifikazioa sortzean: " + e.getMessage(), e);
            }

            final boolean finalInserted = inserted;
            activity.runOnUiThread(() -> {
                callback.onResult(finalInserted); // true si se insertó correctamente
            });
        }).start();
    }







}
