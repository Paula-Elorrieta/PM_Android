package com.example.booksliveapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksliveapp.DB.mysql;
import com.example.booksliveapp.LibrosAdapter;
import com.example.booksliveapp.R;
import com.example.booksliveapp.modelo.Liburua;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements LibrosAdapter.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);


        ArrayList<String> liburuak = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.RecyclerBistaratu);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columnas
        mysql.LiburuakAtera(this, new LiburuaCallback() {
            @Override
            public void onLiburuaLoaded(ArrayList<Liburua> liburuLista) {
                LibrosAdapter adapter = new LibrosAdapter(liburuLista, PrincipalActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_libros) {
                    // acción
                } else if (id == R.id.nav_resenak) {
                    // acción
                }else if (id == R.id.nav_notifications) {
                    Intent intent = new Intent(PrincipalActivity.this, NotifiActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_profile) {
                }

                return false;
            }
        });
    }

    @Override
    public void onItemClick(Liburua libros) {
        Intent intent = new Intent(this, ErosiActivity.class);
        intent.putExtra("liburua", libros);
        intent.putExtra("user", libros.getUser());
        startActivity(intent);
        Toast.makeText(this, "Liburua hautatu: " + libros.getTituloa(), Toast.LENGTH_SHORT).show();
    }

    public interface LiburuaCallback {
        void onLiburuaLoaded(ArrayList<Liburua> liburuak);
    }
}