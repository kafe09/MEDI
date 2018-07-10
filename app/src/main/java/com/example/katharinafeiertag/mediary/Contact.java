package com.example.katharinafeiertag.mediary;

import android.database.Cursor;
import android.util.Log;

//Um verschiedene Benutzer der App in einer Datenbank zu speichern
public class Contact  {

    String vorname,name,email,uname,passwort, fingerPrintenabled;


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

    public void setFingerPrintenabled (String fingerPrintenabled) {this.fingerPrintenabled = fingerPrintenabled;}

    public boolean getFingerPrintenabled() {
        Log.d("Contact", "getFingerprint enabled : " + fingerPrintenabled);
        return this.fingerPrintenabled.equals("1");}

    public Contact(Cursor cursor) {
        vorname = cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_VORNAME));
        name = cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_NAME));
        email = cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_EMAIL));
        uname = cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_UNAME));
        passwort = cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_PASSWORT));
        Log.d("Contact", "Contact ctor, setting fingerprint to " + cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_FINGERPRINTENABLED)));
        fingerPrintenabled = cursor.getString(cursor.getColumnIndex(DatabaseHelperContacts.COLUMN_FINGERPRINTENABLED));
    }

    public Contact() {}
}
