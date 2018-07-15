package com.example.katharinafeiertag.mediary;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

//Unser Hauptmenü
public class HauptmenuActivity extends AppCompatActivity {
    public static final String REQUEST_RESULT = "REQUEST_RESULT";
    private static final String TAG = "AptknaeheActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenu);
    }

    private void init() {
        ImageButton bt_apt = (ImageButton) findViewById(R.id.bt_apt);
        bt_apt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HauptmenuActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    //klick auf Apotheken-Button funktioniert
    public void onApothekenClick(View v) {
        //launchIntent Methode der Aufgabenstellung
        //Art: Action_View: hier wird die Google Maps App geöffnet
        Log.d(TAG, "in der onClick Methode damit man zu Google Maps kommt.");
        Intent apothekenIntent = new Intent(getBaseContext(), MapsActivity.class);
        startActivity(apothekenIntent);
    }

    //klick auf Benutzerprofil-Button funktioniert
    public void onUserClick(View v) {
        //launchIntent2 Methode der Aufgabenstellung
        Intent userIntent = new Intent(getBaseContext(), BenutzerprofilActivity.class);
        startActivity(userIntent);
    }

    //klick auf Hausapotheke-Button
    public void onHausapothekeClick(View v) {
        //Aufgabenstellung: "Daten an eine andere Aktivität übergeben"
        Intent hpIntent = new Intent(this, HausapothekeMenuActivity.class);
        startActivity(hpIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(this,
                    Integer.toString(data.getIntExtra(REQUEST_RESULT, 0)),
                    Toast.LENGTH_LONG).show();
        }
    }
}

