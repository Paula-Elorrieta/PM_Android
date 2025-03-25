package com.example.booksliveapp.modelo;

import java.util.Date;

public class User {
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
}