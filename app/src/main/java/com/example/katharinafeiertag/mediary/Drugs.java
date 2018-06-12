package com.example.katharinafeiertag.mediary;


public class Drugs {

    public String name,art;
    public int medId, menge, nummer;

    public Drugs(int medId, String name, int menge, String art, int nummer) {
        this.medId = medId;
        this.name = name;
        this.menge = menge;
        this.art = art;
        this.nummer = nummer;


    }
    //leerer Konstruktor
    public Drugs() {

    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }


    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }


    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }


}
