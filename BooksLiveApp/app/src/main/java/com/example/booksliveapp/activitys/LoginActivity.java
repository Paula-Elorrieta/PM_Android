package com.example.booksliveapp.activitys;

import static com.example.booksliveapp.DB.mysql.ErabiltzaileaKonprobatu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.sql.SQLException;

import com.example.booksliveapp.R;

public class LoginActivity extends AppCompatActivity {
    boolean exists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button sartuL = findViewById(R.id.buttonLogin);
        EditText erabiltzailea = findViewById(R.id.editTextErabiltzailea);
        EditText pasahitza = findViewById(R.id.editTextPassword);

            sartuL.setOnClickListener(v -> {
                String erabiltzaileaText = erabiltzailea.getText().toString();
                String pasahitzaText = pasahitza.getText().toString();

                try {
                    exists = ErabiltzaileaKonprobatu(erabiltzaileaText, pasahitzaText);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if (exists) {
                    Intent intent = new Intent(this, PrincipalActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "Erabilztailea ez da existitzen", Toast.LENGTH_SHORT).show();
                }
            });
        TextView ErregistroaLink = findViewById(R.id.textErregistroaLink);

        ErregistroaLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, ErregistratuActivity.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}