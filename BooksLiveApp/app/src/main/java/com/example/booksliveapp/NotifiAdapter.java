package com.example.booksliveapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksliveapp.modelo.Notifi;

import java.util.List;

public class NotifiAdapter extends RecyclerView.Adapter<NotifiAdapter.NotifiViewHolder> {

    private List<Notifi> notifikazioak;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Notifi notifi);
    }

    public NotifiAdapter(List<Notifi> notifikazioak, OnItemClickListener listener) {
        this.notifikazioak = notifikazioak;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotifiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notificacion, parent, false); // Usa tu layout de notificaciones aquí
        return new NotifiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifiViewHolder holder, int position) {
        Notifi notifi = notifikazioak.get(position);
        holder.tituloa.setText(notifi.getLiburu().getTituloa());
        holder.mezua.setText(notifi.getMezua());
        holder.data.setText(notifi.getData());
        holder.egoera.setText(notifi.getEgoera());
        holder.erosketaEgiailea.setText(notifi.getErosketaEgiailea().getErabiltzailea());
    }

    @Override
    public int getItemCount() {
        return notifikazioak.size();
    }

    public static class NotifiViewHolder extends RecyclerView.ViewHolder {
        TextView tituloa, mezua, data, egoera, erosketaEgiailea;

        public NotifiViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloa = itemView.findViewById(R.id.tvTitulo);
            mezua = itemView.findViewById(R.id.tvMezua);
            data = itemView.findViewById(R.id.tvFecha);
            egoera = itemView.findViewById(R.id.tvEstado);
            erosketaEgiailea = itemView.findViewById(R.id.tvComprador);
        }
    }
}
