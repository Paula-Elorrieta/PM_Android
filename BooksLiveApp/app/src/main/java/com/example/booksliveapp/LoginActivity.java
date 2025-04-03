package com.example.booksliveapp;

import static com.example.booksliveapp.DB.mysql.ErabiltzaileaKonprobatu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    boolean exists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button sartuL = findViewById(R.id.SartuL);
        EditText erabiltzailea = findViewById(R.id.ErabilztaileaL);
        EditText pasahitza = findViewById(R.id.PasswordL);

            sartuL.setOnClickListener(v -> {
                String erabiltzaileaText = erabiltzailea.getText().toString();
                String pasahitzaText = pasahitza.getText().toString();

                try {
                    exists = ErabiltzaileaKonprobatu(erabiltzaileaText, pasahitzaText);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (exists) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}