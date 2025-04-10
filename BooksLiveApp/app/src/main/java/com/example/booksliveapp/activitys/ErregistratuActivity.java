package com.example.booksliveapp.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.booksliveapp.R;

import java.sql.Date;
import java.util.Calendar;

public class ErregistratuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_erregistratu);

        Button erregistratuButton = findViewById(R.id.buttonErregistratu);
        Button buttonDatePicker = findViewById(R.id.buttonDatePicker);
        TextView textViewDate = findViewById(R.id.textViewDate);


        buttonDatePicker.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ErregistratuActivity.this,
                    (view, selectedYear, selectedMonth, dayOfMonth) -> {
                        // Format and set the selected date
                        String fechaSeleccionada = selectedYear + "-" + (selectedMonth + 1) + "-" + dayOfMonth;
                        Date date = Date.valueOf(fechaSeleccionada);
                        textViewDate.setText(date.toString());
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });


        erregistratuButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PrincipalActivity.class);
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