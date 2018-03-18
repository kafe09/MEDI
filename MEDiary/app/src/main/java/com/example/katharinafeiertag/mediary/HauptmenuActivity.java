package com.example.katharinafeiertag.mediary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HauptmenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenu);
    }


    //klick auf Apotheken-Button funktioniert
    public void onApothekenClick (View v){
        //launchIntent Methode der Aufgabenstellung
        //Art: Action_View: hier wird die Google Maps App geöffnet
        Intent apothekenIntent = new Intent(Intent.ACTION_VIEW);
        apothekenIntent.setData(Uri.parse("https://www.google.com/maps"));
        startActivity(apothekenIntent);
    }

    //klick auf Benutzerprofil-Button funktioniert
    public void onUserClick (View v){
        //launchIntent2 Methode der Aufgabenstellung
        Intent userIntent = new Intent(getBaseContext(), BenutzerprofilActivity.class);
        startActivity(userIntent);
    }

    //klick auf Settings-Button
    public void onSettingsClick (View v){
        // Intent settingsIntent = new Intent(this, SettingsActivity.class);
        Intent settingsIntent = new Intent (getBaseContext(), EinstellungenActivity.class);
        startActivity(settingsIntent);
    }

    //klick auf MedEinnahme-Button !! GEHT NOCH NICHT !!
    public void onMedEinnahmeClick (View v){
        startActivity(new Intent(this, EinnahmeActivity.class));
    }

    //klick auf Hausapotheke-Button
    public void onHausapothekeClick (View v){
        //Aufgabenstellung: "Daten an eine andere Aktivität übergeben"
        // /*final*/ EditText tf_vorhanden = (EditText) findViewById(android.R.id.tf_editText);
       // String userMessage = tf_vorhanden.getText().toString();
        Intent hpIntent = new Intent (this,HausapothekeActivity.class);
        //hpIntent.putExtra("Hier sehen Sie Ihre Hausapotheke", userMessage);
       // hpIntent.putExtra(Intent.EXTRA_TEXT, userMessage);
        startActivity(hpIntent);
    }
}
