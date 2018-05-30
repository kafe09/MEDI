package com.example.katharinafeiertag.mediary;


//für die persönliche Hausapotheke des Benutzers (Datenbank der eigenen Medikamente)

public class MedikamentNeu {
    String handelsname,menge,art,zulassungsnummer;

    public MedikamentNeu(String handelsname) {
        this.handelsname = handelsname;
    }

    public MedikamentNeu() {

    }

    public String getHandelsname() {
        return handelsname;
    }

    public void setHandelsname(String handelsname) {
        this.handelsname = handelsname;
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

    public String getZulassungsnummer() {
        return zulassungsnummer;
    }

    public void setZulassungsnummer(String zulassungsnummer) {
        this.zulassungsnummer = zulassungsnummer;
    }
}
