package com.example.katharinafeiertag.mediary;


//für die persönliche Hausapotheke des Benutzers (Datenbank der eigenen Medikamente)

public class MedikamentNeu {
    private String handelsname,art;
    private int menge;

    public MedikamentNeu(String handelsname, String art, int menge) {
        this.handelsname = handelsname;
        this.menge = menge;
        this.art = art;
    }

    public MedikamentNeu() {

    }

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



}
