package com.example.katharinafeiertag.mediary;

/**
 * Created by scheerbernhard on 30.03.18.
 */

public class Contact  {

    String vorname,name,email,passwort;
    //char geschlecht;

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

public void setPasswort(String passwort) {
    this.passwort = passwort;
}
public String getPasswort() {
    return this.passwort;
}



/*public void setGeschlecht(Character geschlecht) {
    this.geschlecht = geschlecht;
}
public Character getGeschlecht() {
    return this.geschlecht;
}*/
}
