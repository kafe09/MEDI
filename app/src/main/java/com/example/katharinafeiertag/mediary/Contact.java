package com.example.katharinafeiertag.mediary;

//Um verschiedene Benutzer der App in einer Datenbank zu speichern
public class Contact  {
    String vorname,name,email,uname,passwort;


    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return this.uname;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getPasswort() {
        return this.passwort;
    }
}
