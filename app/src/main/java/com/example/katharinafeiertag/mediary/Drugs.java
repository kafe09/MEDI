package com.example.katharinafeiertag.mediary;


public class Drugs {

    public int medId;
    public String name, menge, art, nummer;

    public Drugs(int medId, String name, String menge, String art, String nummer) {
        this.medId = medId;
        this.name = name;
        this.menge = menge;
        this.art = art;
        this.nummer = nummer;


    }
    //leerer Konstruktor
    public Drugs() {

    }

    public int getMedID() {
        return medId;
    }

    public void setMedID(int medId) {
        this.medId = medId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMenge() {
        return menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }


    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }


    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }


}
