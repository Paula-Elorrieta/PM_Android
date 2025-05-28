package com.example.booksliveapp.activitys;

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

import com.example.booksliveapp.DB.mysql;
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
         TextView ErregistroaLink = findViewById(R.id.textErregistroaLink);
        TextView ErregistratuGabe = findViewById(R.id.textErregistratuGabeLink);

        sartuL.setOnClickListener(v -> {
            String erabiltzaileaText = erabiltzailea.getText().toString();
            String pasahitzaText = pasahitza.getText().toString();

            if(erabiltzaileaText.isEmpty() || pasahitzaText.isEmpty()){
                Toast.makeText(this, "Sartu erabiltzailea eta pasahitza", Toast.LENGTH_SHORT).show();
                return;
            }

            mysql.ErabiltzaileaKonprobatu(erabiltzaileaText, pasahitzaText, this, new mysql.Callback() {
                @Override
                public void onResult(boolean exists) {
                    LoginActivity.this.exists = exists;
                    if (exists) {

                        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Erabilztailea ez da existitzen", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


            ErregistroaLink.setOnClickListener(view -> {
                Intent intent = new Intent(LoginActivity.this, ErregistratuActivity.class);
                startActivity(intent);
                finish();
            });

            ErregistratuGabe.setOnClickListener(view -> {
                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            });



            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

    }
}
