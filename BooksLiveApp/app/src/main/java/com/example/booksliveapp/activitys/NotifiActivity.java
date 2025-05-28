package com.example.booksliveapp.activitys;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksliveapp.DB.mysql;
import com.example.booksliveapp.LibrosAdapter;
import com.example.booksliveapp.NotifiAdapter;
import com.example.booksliveapp.R;
import com.example.booksliveapp.modelo.Liburua;
import com.example.booksliveapp.modelo.Notifi;

import java.util.ArrayList;

public class NotifiActivity extends AppCompatActivity implements NotifiAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifi);

        //Button ezeztatu = findViewById(R.id.ezeztatuButton);

        RecyclerView recyclerView = findViewById(R.id.rvNotificaciones);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columnas
        mysql.NotifikazioakAtera(this, new NotifiActivity.NotifikazioCallback() {
            @Override
            public void onNotifikazioakLoaded(ArrayList<Notifi> notifikazioLista) {
                NotifiAdapter adapter = new NotifiAdapter(notifikazioLista, NotifiActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onItemClick(Notifi notifi) {

    }

    public interface NotifikazioCallback {
        void onNotifikazioakLoaded(ArrayList<Notifi> notifikazioLista);
    }
}