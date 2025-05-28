package com.example.booksliveapp.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class User  implements Parcelable {
    private int userId;
    private String izenaAbizena;
    private String email;
    private String pasahitza;
    private String erabiltzailea;
    private Date jaiotzaData;
    private String helbidea;

    public User(int userId, String izenaAbizena, String email, String pasahitza, String erabiltzailea, Date jaiotzaData, String helbidea) {
        this.userId = userId;
        this.izenaAbizena = izenaAbizena;
        this.email = email;
        this.pasahitza = pasahitza;
        this.erabiltzailea = erabiltzailea;
        this.jaiotzaData = jaiotzaData;
        this.helbidea = helbidea;
    }

    public User() {

    }

    protected User(Parcel in) {
        userId = in.readInt();
        izenaAbizena = in.readString();
        email = in.readString();
        pasahitza = in.readString();
        erabiltzailea = in.readString();
        helbidea = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIzenaAbizena() {
        return izenaAbizena;
    }

    public void setIzenaAbizena(String izenaAbizena) {
        this.izenaAbizena = izenaAbizena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    public String getErabiltzailea() {
        return erabiltzailea;
    }

    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    public Date getJaiotzaData() {
        return jaiotzaData;
    }

    public void setJaiotzaData(Date jaiotzaData) {
        this.jaiotzaData = jaiotzaData;
    }

    public String getHelbidea() {
        return helbidea;
    }

    public void setHelbidea(String helbidea) {
        this.helbidea = helbidea;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(izenaAbizena);
        dest.writeString(email);
        dest.writeString(pasahitza);
        dest.writeString(erabiltzailea);
        dest.writeString(helbidea);
    }
}