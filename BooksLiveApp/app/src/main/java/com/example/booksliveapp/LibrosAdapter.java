package com.example.booksliveapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.ElementuaViewHolder> {

    private final ArrayList<String> liburuak; // Lista de objetos lenguaia
    private final OnItemClickListener listener;

    // Interfaz para manejar clics
    public interface OnItemClickListener {
        void onItemClick(ArrayList<String> liburuak);
    }

    // Constructor del adaptador
    public LibrosAdapter(ArrayList<String> liburuak, OnItemClickListener listener) {
        this.liburuak = liburuak;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElementuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar la vista del ítem
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liburuak_item, parent, false);
        return new ElementuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementuaViewHolder holder, int position) {
        // Obtener el objeto en la posición actual
        String current = liburuak.get(position);
        holder.bind(current, listener); // Configurar la vista y el clic
    }

    @Override
    public int getItemCount() {
        return liburuak.size(); // Tamaño de la lista
    }

    // Clase interna para gestionar la vista de cada elemento
    public static class ElementuaViewHolder extends RecyclerView.ViewHolder {

        public ElementuaViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemText = itemView.findViewById(R.id.textViewIzena);
//            itemDeskribapena = itemView.findViewById(R.id.textDeskribapena);
        }

        public void bind(final String lenguaia, final OnItemClickListener listener) {
            // Configurar el texto y la acción de clic
//            itemText.setText(lenguaia.getIzena()); // Mostrar el nombre en el TextView
//            itemDeskribapena.setText(lenguaia.getDeskribapena()); // Mostrar la descripción en el TextView
//            itemView.setOnClickListener(v -> listener.onItemClick(lenguaia)); // Pasar el objeto completo
        }
    }
}
