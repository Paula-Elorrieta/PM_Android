package com.example.booksliveapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksliveapp.DB.mysql;
import com.example.booksliveapp.LibrosAdapter;
import com.example.booksliveapp.R;
import com.example.booksliveapp.modelo.Liburua;

import java.security.Principal;
import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements LibrosAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        Button logoutButton = findViewById(R.id.buttonItzuli);

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        ArrayList<String> liburuak = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.RecyclerBistaratu);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columnas
        mysql.LiburuakAtera(this, new LiburuaCallback() {
            @Override
            public void onLiburuaLoaded(ArrayList<Liburua> liburuak) {
                for (Liburua libro : liburuak) {
                    liburuak.add(libro);
                }

                LibrosAdapter adapter = new LibrosAdapter(liburuak, PrincipalActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public void onItemClick(String item) {
        // Handle item click
//        Intent intent = new Intent(this, LiburuaActivity.class);
//        intent.putExtra("liburua", item);
//        startActivity(intent);
    }

    public interface LiburuaCallback {
        void onLiburuaLoaded(ArrayList<Liburua> liburuak);
    }
}