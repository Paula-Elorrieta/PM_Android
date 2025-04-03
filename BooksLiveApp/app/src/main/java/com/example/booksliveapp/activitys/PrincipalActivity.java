package com.example.booksliveapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booksliveapp.LibrosAdapter;
import com.example.booksliveapp.LoginActivity;
import com.example.booksliveapp.R;
import com.example.booksliveapp.modelo.Liburua;

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

        // Datuak prestatu
//        liburuak.add(new Liburua(1, "Objektu orientatutako programazio lengoaia, erabilera anitza eta erraza.", "Librea", "fiktizoa", 2.09,""));
//
//        // Configurar RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.RecyclerBistaratu);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapterList = new ElementuaAdapter(liburuak, (LibrosAdapter.OnItemClickListener) this);
//        recyclerView.setAdapter(adapterList);

        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onItemClick(ArrayList<String> liburuak) {

    }

}