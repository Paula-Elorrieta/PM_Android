package com.example.booksliveapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksliveapp.activitys.PrincipalActivity;
import com.example.booksliveapp.modelo.Liburua;

import java.util.List;
import java.util.Locale;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibroViewHolder> {

    private List<Liburua> libros;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Liburua libros);
    }

    public LibrosAdapter(List<Liburua> libros, PrincipalActivity listener) {
        this.libros = libros;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.liburuak_item, parent, false);
        return new LibroViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Liburua libruru = libros.get(position);
        holder.titulo.setText(libruru.getTituloa());
        holder.egoera.setText(libruru.getEgoera());
        holder.erabiltzailea.setText(libruru.getUser().getErabiltzailea());
        holder.precio.setText(String.format(Locale.getDefault(), "%.2f €", libruru.getPrezioa()));
        int imageResId = holder.imagen.getContext()
                .getResources()
                .getIdentifier(libruru.getIrudia(), "drawable", holder.imagen.getContext().getPackageName());

        // Si se encuentra, mostrar la imagen
        if (imageResId != 0) {
            holder.imagen.setImageResource(imageResId);
        } else {
            holder.imagen.setImageResource(R.drawable.sin_foto); // opcional
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(libruru);
            }
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, egoera, precio, erabiltzailea;
        ImageView imagen;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.productTitle);
            egoera = itemView.findViewById(R.id.productEgoera);
            precio = itemView.findViewById(R.id.Price);
            imagen = itemView.findViewById(R.id.productImage);
            erabiltzailea = itemView.findViewById(R.id.productUser);
        }
    }
}