package com.example.booksliveapp.modelo;

public class Liburua {
    private int liburuId;
    private String tituloa;
    private String idazlea;
    private String generoa;
    private double prezioa;
    private String egoera;
    private User user;

    public Liburua(int liburuId, String tituloa, String idazlea, String generoa, double prezioa, String egoera, User user) {
        this.liburuId = liburuId;
        this.tituloa = tituloa;
        this.idazlea = idazlea;
        this.generoa = generoa;
        this.prezioa = prezioa;
        this.egoera = egoera;
        this.user = user;
    }

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
}
