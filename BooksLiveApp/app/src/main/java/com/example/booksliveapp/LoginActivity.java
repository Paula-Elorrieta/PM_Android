package com.example.booksliveapp;

<<<<<<< HEAD
import static com.example.booksliveapp.DB.mysql.ErabiltzaileaKonprobatu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
=======
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
>>>>>>> ff846797339835609f9f97b6edbd91aaafdf182f

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
import java.sql.SQLException;
=======
import com.example.booksliveapp.activitys.ErregistratuActivity;
import com.example.booksliveapp.activitys.PrincipalActivity;

public class LoginActivity extends AppCompatActivity {
>>>>>>> ff846797339835609f9f97b6edbd91aaafdf182f

public class LoginActivity extends AppCompatActivity {
    boolean exists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
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

=======
        TextView ErregistroaLink = findViewById(R.id.textErregistroaLink);
        Button loginButton = findViewById(R.id.buttonLogin);

        ErregistroaLink.setOnClickListener(v -> {
            Intent intent = new Intent(this, ErregistratuActivity.class);
            startActivity(intent);
            finish();
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        });
>>>>>>> ff846797339835609f9f97b6edbd91aaafdf182f

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}