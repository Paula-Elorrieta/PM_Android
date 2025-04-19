package com.example.booksliveapp.activitys;

import android.app.DatePickerDialog;
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

import java.sql.Date;
import java.util.Calendar;

public class ErregistratuActivity extends AppCompatActivity {
    Date date;
    String fechaSeleccionada;
    boolean exists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_erregistratu);

        Button erregistratuButton = findViewById(R.id.buttonErregistratu);
        Button buttonDatePicker = findViewById(R.id.buttonDatePicker);
        TextView textViewDate = findViewById(R.id.textViewDate);
        EditText textIzena = findViewById(R.id.editTextIzAbiz);
        String izenAbizen = textIzena.toString();
        EditText textEmail = findViewById(R.id.editTextEmail);
        String Email = textEmail.toString();
        EditText textErabiltzailea = findViewById(R.id.editTextErabiltzaileaE);
        String Erabiltzailea = textErabiltzailea.toString();
        EditText textpasahitza = findViewById(R.id.editTextPassword);
        String pasahitza = textpasahitza.toString();
        EditText texthelbidea = findViewById(R.id.editTextHelbidea);
        String helbidea = texthelbidea.toString();




        buttonDatePicker.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ErregistratuActivity.this,
                    (view, selectedYear, selectedMonth, dayOfMonth) -> {
                        // Format and set the selected date
                         fechaSeleccionada = selectedYear + "-" + (selectedMonth + 1) + "-" + dayOfMonth;
                         date = Date.valueOf(fechaSeleccionada);
                        textViewDate.setText(date.toString());
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });




        erregistratuButton.setOnClickListener(v -> {
            if(izenAbizen.isEmpty()){
                textIzena.setError("Idatzi zure izena eta abizena");
            }else if (Email.isEmpty()){
                textEmail.setError("Idatzi sure Email");
            }else if(Erabiltzailea.isEmpty()){
                textErabiltzailea.setError("Idatzi erabiltzailea");
            }else if(pasahitza.isEmpty()){
                textpasahitza.setError("Idatzi pasahitza");
            }else if(fechaSeleccionada.isEmpty()){
               textViewDate.setError("Idatzi jaiotza-data");
            }else if (helbidea.isEmpty()){
                texthelbidea.setError("Idatzi helbidea");
            }


            mysql.ErabiltzaileaSortu(izenAbizen,Erabiltzailea,Email,pasahitza,helbidea,date,this,new mysql.Callback() {
                @Override
                public void onResult(boolean exists) {
                    ErregistratuActivity.this.exists = exists;
                    if (exists) {
                        Toast.makeText(ErregistratuActivity.this, "Erabilztailea erregistratuta dago", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(ErregistratuActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });


        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}