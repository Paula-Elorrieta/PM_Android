package com.example.booksliveapp.DB;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.booksliveapp.activitys.LoginActivity;
import com.example.booksliveapp.modelo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mysql {
    private static final String URL = "jdbc:mysql://192.168.1.145:3307/db_bookslive";
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
                            SharedPreferences preferences = activity.getSharedPreferences("User", MODE_PRIVATE);
                            SharedPreferences.Editor loginPrefsEditor = preferences.edit();
                            loginPrefsEditor.putString("erabiltzailea", user.getErabiltzailea());
                            loginPrefsEditor.apply();
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
}
