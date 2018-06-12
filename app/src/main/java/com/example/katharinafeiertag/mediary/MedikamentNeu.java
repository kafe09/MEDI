package com.example.katharinafeiertag.mediary;


//für die persönliche Hausapotheke des Benutzers (Datenbank der eigenen Medikamente)

public class MedikamentNeu {

    private String medid, handelsname,art;
    private int menge,nummer;

    public MedikamentNeu(String medid, String handelsname, String art, int menge, int nummer) {
        this.medid = medid;
        this.handelsname = handelsname;
        this.menge = menge;
        this.art = art;
        this.nummer = nummer;
    }

    public MedikamentNeu() {

    }

    public String getMedid() {return medid;}
    public void setMedid(String medid) {this.medid=medid;}

    public String getHandelsname() {
        return handelsname;
    }

    public void setHandelsname(String handelsname) {
        this.handelsname = handelsname;
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

    public int getNummer() {return nummer;}

    public void setNummer(int nummer) {this.nummer = nummer;}





}
