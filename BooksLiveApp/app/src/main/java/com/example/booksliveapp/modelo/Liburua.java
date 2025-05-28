package com.example.booksliveapp.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Liburua implements Parcelable {
    private int liburuId;
    private String tituloa;
    private String idazlea;
    private String generoa;
    private double prezioa;
    private String egoera;
    private User user;
    private String irudia; // Añadido para la imagen del libro

    public Liburua(int liburuId, String tituloa, String idazlea, String generoa, double prezioa, String egoera, User user, String irudia) {
        this.liburuId = liburuId;
        this.tituloa = tituloa;
        this.idazlea = idazlea;
        this.generoa = generoa;
        this.prezioa = prezioa;
        this.egoera = egoera;
        this.user = user;
        this.irudia = irudia; // Inicializar la imagen del libro
    }

    public Liburua() {
        // Constructor por defecto
    }

    protected Liburua(Parcel in) {
        liburuId = in.readInt();
        tituloa = in.readString();
        idazlea = in.readString();
        generoa = in.readString();
        prezioa = in.readDouble();
        egoera = in.readString();
        irudia = in.readString();
    }

    public static final Creator<Liburua> CREATOR = new Creator<Liburua>() {
        @Override
        public Liburua createFromParcel(Parcel in) {
            return new Liburua(in);
        }

        @Override
        public Liburua[] newArray(int size) {
            return new Liburua[size];
        }
    };

    public int getLiburuId() {
        return liburuId;
    }

    public void setLiburuId(int liburuId) {
        this.liburuId = liburuId;
    }

    public String getTituloa() {
        return tituloa;
    }

    public void setTituloa(String tituloa) {
        this.tituloa = tituloa;
    }

    public String getIdazlea() {
        return idazlea;
    }

    public void setIdazlea(String idazlea) {
        this.idazlea = idazlea;
    }

    public String getGeneroa() {
        return generoa;
    }

    public void setGeneroa(String generoa) {
        this.generoa = generoa;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public String getEgoera() {
        return egoera;
    }

    public void setEgoera(String egoera) {
        this.egoera = egoera;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIrudia() {
        return irudia;
    }
    public void setIrudia(String irudia) {
        this.irudia = irudia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(liburuId);
        dest.writeString(tituloa);
        dest.writeString(idazlea);
        dest.writeString(generoa);
        dest.writeDouble(prezioa);
        dest.writeString(egoera);
        dest.writeString(irudia);
    }
}
