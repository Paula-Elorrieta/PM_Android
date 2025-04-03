package com.example.booksliveapp.DB;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.booksliveapp.modelo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mysql {
    private static final String URL = "jdbc:mysql://localhost:3307/db_bookslive";
    private static final String USER = "user";
    private static final String PASSWORD = "";
    private static Context context;


    public static boolean ErabiltzaileaKonprobatu(String erabiltzailea, String pasahitza) throws SQLException {
        boolean exists = false;
        String sql = "SELECT * FROM users WHERE erabiltzailea = ? AND pasahitza = ?";
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
                    SharedPreferences preferences = context.getSharedPreferences("User", MODE_PRIVATE);

                    SharedPreferences.Editor loginPrefsEditor = preferences.edit();
                    loginPrefsEditor.putString("erabiltzailea", user.getErabiltzailea());
                    loginPrefsEditor.apply();

                }
                Log.d("MYSQL", "Erabiltzailea aurkitu da: " + user.getErabiltzailea());

            }

        } catch (SQLException e) {
            Log.e("MYSQL", "Errorea: " + e.getMessage(), e);
        }

        return exists;
    }

    public static boolean ErabiltzaileaErregistratu(String erabiltzailea, String pasahitza, String izenaAbizena, String email, String helbidea) throws SQLException {
        boolean exists = false;
        String sql = "INSERT INTO users (erabiltzailea, pasahitza, izena_abizena, email, helbidea) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, erabiltzailea);
            preparedStatement.setString(2, pasahitza);
            preparedStatement.setString(3, izenaAbizena);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, helbidea);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                exists = true;
                Log.d("MYSQL", "Erabiltzailea erregistratu da: " + erabiltzailea);
            }
        } catch (SQLException e) {
            Log.e("MYSQL", "Errorea: " + e.getMessage(), e);
        }
        return exists;
    }
}
