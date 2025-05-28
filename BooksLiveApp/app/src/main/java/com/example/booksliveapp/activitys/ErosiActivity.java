package com.example.booksliveapp.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.booksliveapp.DB.mysql;
import com.example.booksliveapp.R;
import com.example.booksliveapp.modelo.Liburua;
import com.example.booksliveapp.modelo.User;
import com.example.booksliveapp.variables;

public class ErosiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_erosi);

        Liburua liburua = getIntent().getParcelableExtra("liburua");
        User user = getIntent().getParcelableExtra("user");
         TextView tituloTextView = findViewById(R.id.productTitle);
        TextView idazleaTextView = findViewById(R.id.productAuthor);
        TextView generoaTextView = findViewById(R.id.productGenre);
        TextView prezioaTextView = findViewById(R.id.productPrice);
        TextView egoeraTextView = findViewById(R.id.productEgoera);
        ImageView irudiaImageView = findViewById(R.id.productImage);
        TextView userTextView = findViewById(R.id.productUser);
        Button itzuli = findViewById(R.id.backButton);
        Button erosiButton = findViewById(R.id.buyButton);

        itzuli.setOnClickListener(v -> {
            finish(); // Cierra la actividad actual y vuelve a la anterior
        });

        if (liburua != null) {
            tituloTextView.setText(liburua.getTituloa());
            idazleaTextView.setText(liburua.getIdazlea());
            generoaTextView.setText(liburua.getGeneroa());
            prezioaTextView.setText(String.format("%.2f €", liburua.getPrezioa()));
            egoeraTextView.setText(liburua.getEgoera());
            userTextView.setText(user != null ? "Argitaratzailea: " + user.getErabiltzailea() : "Argitaratzailea: Desconocido");
            int imageResId = getResources().getIdentifier(liburua.getIrudia(), "drawable", getPackageName());
            // Si se encuentra, mostrar la imagen
            if (imageResId != 0) {
                irudiaImageView.setImageResource(imageResId);
            } else {
                irudiaImageView.setImageResource(R.drawable.sin_foto); // Imagen por defecto si no se encuentra
            }
        }

        erosiButton.setOnClickListener(v -> {

            mysql.NotifikazioaSortu(liburua.getLiburuId(), variables.LoginUser.getUserId(),user.getUserId(),liburua.getTituloa(),this, new mysql.Callback() {
                @Override
                public void onResult(boolean success) {
                    if (success) {
                        Toast.makeText(ErosiActivity.this, "Erosi arrakastatsua!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ErosiActivity.this, "Erosketa huts egin du", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}