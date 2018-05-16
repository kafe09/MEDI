package com.example.katharinafeiertag.mediary;


public class Drugs {

    public String pharmaId,name,menge,art,preis,code,bezeichnung;

    public Drugs(String pharmaId, String name, String menge, String art, String preis, String code, String bezeichnung) {
        this.pharmaId = pharmaId;
        this.name = name;
        this.menge = menge;
        this.art = art;
        this.preis = preis;
        this.code = code;
        this.bezeichnung = bezeichnung;

    }
    //leerer Konstruktor
    public Drugs() {

    }

    public String getPharmaId() {
        return pharmaId;
    }

    public void setPharmaId(String pharmaId) {
        this.pharmaId = pharmaId;
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

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
