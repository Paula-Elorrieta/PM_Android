package com.example.booksliveapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksliveapp.modelo.Liburua;

import java.util.ArrayList;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.ElementuaViewHolder> {

    private final ArrayList<Liburua> liburuak;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public LibrosAdapter(ArrayList<Liburua> liburuak, OnItemClickListener listener) {
        this.liburuak = liburuak;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElementuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liburuak_item, parent, false);
        return new ElementuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementuaViewHolder holder, int position) {
        String current = String.valueOf(liburuak.get(position));
        holder.title.setText(current);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(current));
    }

    @Override
    public int getItemCount() {
        return liburuak.size();
    }

    public static class ElementuaViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ElementuaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.menuTitle); // Asegúrate de tener este ID en liburuak_item.xml
        }
    }
}
