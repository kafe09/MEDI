package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Hier kann sich der Benutzer neu registrieren und wird in einer Datenbank gespeichert
public class RegistrierungActivity extends AppCompatActivity {
    DatabaseHelperContacts helper = new DatabaseHelperContacts(this);
    EditText vorname, name, email, uname, passwort, passwort2;
    String vornamestr, namestr, emailstr, unamestr, passwortstr, passwort2str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);
        helper = new DatabaseHelperContacts(this);

        vorname = (EditText) findViewById(R.id.tf_vn);

        name = (EditText) findViewById(R.id.tf_nm);

        email = (EditText) findViewById(R.id.tf_mail);

        uname = (EditText) findViewById(R.id.tf_uname);

        passwort = (EditText) findViewById(R.id.tf_passwort);

        passwort2 = (EditText) findViewById(R.id.tf_passwort2);

    }

    public void onRegistrierenClick(View v) {
        if (checkData()) {

            Intent regiintent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(regiintent);

            //insert details in database
            Contact c = new Contact();
            c.setVorname(vornamestr);
            c.setName(namestr);
            c.setEmail(emailstr);
            c.setUname(unamestr);
            c.setPasswort(passwortstr);
            c.setFingerPrintenabled("false");
            helper.insertContact(c);
        }
    }

    public boolean checkData() {
        vornamestr = vorname.getText().toString();
        namestr = name.getText().toString();
        emailstr = email.getText().toString();
        unamestr = uname.getText().toString();
        passwortstr = passwort.getText().toString();
        passwort2str = passwort2.getText().toString();

        if (vornamestr.isEmpty() || namestr.isEmpty() || emailstr.isEmpty() || unamestr.isEmpty() || passwortstr.isEmpty() || passwort2str.isEmpty()) {
            displayToast("Sie müssen alle Felder ausfüllen!");
            return false;
        } else if (!passwortstr.equals(passwort2str)) {
            displayToast("Passwörter stimmen nicht überein!");
            return false;
        } else if (!isAlpha(vornamestr) || !isAlpha(namestr)) {
            displayToast("Namen dürfen nur aus Buchstaben bestehen");
            return false;
        } else if (!checkEmai(emailstr)) {
            return false;
        }
        return true;
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public boolean isAlpha(String text) {
        for (char c : text.toCharArray()) {
            // a - z
            if (c >= 'a' && c <= 'z')
                continue;
            // A - Z
            if (c >= 'A' && c <= 'Z')
                continue;
            // ö, ü, ä, ß
            if (c == 'ö' || c == 'ß' || c == 'ä' || c == 'ü')
                continue;
            return false;
        }
        return true;
    }

    public boolean checkEmai(String email) {
        //EMAIL
        Log.d("Email", "Benutzereingabe" + email);

        String[] mailPattern = new String[]{
                "^[a-zA-Z0-9._@-]*$",                           //keine Sonderzeichen
                "^([a-zA-Z0-9._-]*)@([a-zA-Z0-9._-]*)$",        //Die Email muss genau ein „@“ Zeichen enthalten
                ".*\\..*",                                      //Die Email muss mind. einen Punkt enthalten
                ".*@[^.].*",                                    //Die Domain darf nicht mit einem . (Punkt) beginnen oder enden
                "^[^.].*",                                      //Die Email darf nicht mit einem Punkt beginnen
                ".*[^.]@.*",                                    //Die Email darf nicht mit einem Punkt enden
                "^([^.]+\\.)*[^.]+@.*$",                        //Zwei aufeinanderfolgende Punkte
                ".*\\.([a-z]{2,})$",                            //Die Domainendung muss mindestens zwei Buchstaben haben
                ".*@[a-zA-Z0-9]+\\.[^.]*",                      //Nur Buchstaben und Zahlen sind für die Domain zulässig
                "^[a-zA-Z0-9.-_]+@.*"};                         //Nur Buchstaben, Zahlen, Unterstrich (_), Punkt (.) und Minus (-) sind als Email erlaubt

        String[] mailError = new String[]{
                "Eine Email Adresse kann nicht aus Sonderzeichen oder Leerschritten bestehen",
                "Die Email muss genau ein „@“ Zeichen enthalten",
                "Die Email muss mind. einen Punkt enthalten",
                "Die Domain darf nicht mit einem . (Punkt) beginnen oder enden",
                "Die Email darf nicht mit einem Punkt beginnen",
                "Die Email darf nicht mit einem Punkt enden",
                "Mehrere aufeinanderfolgende Punkte sind nicht erlaubt",
                "Die Domainendung muss mindestens zwei Buchstaben haben und darf keine Zahlen enthalten",
                "Nur Buchstaben und Zahlen sind für die Domain zulässig",
                "Nur Buchstaben, Zahlen, Unterstrich (_), Punkt (.) und Minus (-) sind als Email erlaubt"
        };

        for (int i = 0; i < mailPattern.length; i++) {
            System.out.println(mailPattern[i]);
            Pattern mailP = Pattern.compile(mailPattern[i]);
            Matcher mailM = mailP.matcher((CharSequence) email);

            if (!mailM.matches()) {
                displayToast(mailError[i]);
                return false;
            }
        }
        return true;
    }

    public void test () {

    }
}


