package com.example.booksliveapp.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Notifi implements Parcelable {
    private int id;
    private Liburua liburu;
    private User erosketaEgiailea;
    private User saltzailea;
    private String egoera;     // puede ser 'Zain', 'Onartua', 'Ezeztatuta'
    private String mezua;
    private String data;

    public Notifi(int id, Liburua liburu, User erosketaEgiailea, User saltzailea,
                        String egoera, String mezua, String data) {
        this.id = id;
        this.liburu = liburu;
        this.erosketaEgiailea = erosketaEgiailea;
        this.saltzailea = saltzailea;
        this.egoera = egoera;
        this.mezua = mezua;
        this.data = data;
    }

    // Getters y setters

    protected Notifi(Parcel in) {
        id = in.readInt();
        egoera = in.readString();
        mezua = in.readString();
        data = in.readString();
    }

    public Notifi() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(egoera);
        dest.writeString(mezua);
        dest.writeString(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notifi> CREATOR = new Creator<Notifi>() {
        @Override
        public Notifi createFromParcel(Parcel in) {
            return new Notifi(in);
        }

        @Override
        public Notifi[] newArray(int size) {
            return new Notifi[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Liburua getLiburu() {
        return liburu;
    }

    public void setLiburu(Liburua liburu) {
        this.liburu = liburu;
    }

    public User getErosketaEgiailea() {
        return erosketaEgiailea;
    }

    public void setErosketaEgiailea(User erosketaEgiailea) {
        this.erosketaEgiailea = erosketaEgiailea;
    }

    public User getSaltzailea() {
        return saltzailea;
    }

    public void setSaltzailea(User saltzailea) {
        this.saltzailea = saltzailea;
    }

    public String getEgoera() {
        return egoera;
    }

    public void setEgoera(String egoera) {
        this.egoera = egoera;
    }

    public String getMezua() {
        return mezua;
    }

    public void setMezua(String mezua) {
        this.mezua = mezua;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
