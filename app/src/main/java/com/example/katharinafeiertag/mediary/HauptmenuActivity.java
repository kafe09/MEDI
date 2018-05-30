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
    //Aufgabenstellung: Ein Ergebnis aus einer Aktivität zurückgeben
    public static final String REQUEST_RESULT = "REQUEST_RESULT";

    private static final String TAG = "AptknaeheActivity";

    private static final int ERROR_DIADLOG_REQUEST = 9011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenu);
//        if(isServiceOK()){
//            init();
//        }
    }

    private void init() {
        ImageButton bt_apt = (ImageButton) findViewById(R.id.bt_apt);
        bt_apt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HauptmenuActivity.this, AptknaeheActivity.class);
                startActivity(intent);
            }
        });
    }


    //klick auf Apotheken-Button funktioniert
    public void onApothekenClick(View v) {
        //launchIntent Methode der Aufgabenstellung
        //Art: Action_View: hier wird die Google Maps App geöffnet
        Log.d(TAG, "in der onClick Methode damit man zu Google Maps kommt.");
        Intent apothekenIntent = new Intent(getBaseContext(), AptknaeheActivity.class);
        startActivity(apothekenIntent);
    }

    //klick auf Benutzerprofil-Button funktioniert
    public void onUserClick(View v) {
        //launchIntent2 Methode der Aufgabenstellung
        Intent userIntent = new Intent(getBaseContext(), BenutzerprofilActivity.class);
        startActivity(userIntent);
    }

    //klick auf Settings-Button
    public void onSettingsClick(View v) {
        Intent settingsIntent = new Intent(getBaseContext(), EinstellungenActivity.class);
        startActivity(settingsIntent);
    }

    //klick auf MedEinnahme-Button
    //Aufgabenstellung: onClickSwitchActivity()
    public void onMedEinnahmeClick(View v) {
        Intent einnahmeIntent = new Intent(this, EinnahmeActivity.class);
        startActivityForResult(einnahmeIntent, 1);
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

   /* public boolean isServiceOK() {
        Log.d(TAG, "isServiceOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(AptknaeheActivity.this);

        if (available  == ConnectionResult.SUCCESS) {
            //erverything is fine and the user can make requests
            Log.d(TAG, "isServiceOK: Google Play Services is working");
            return true;

        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServiceOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(AptknaeheActivity.this,available, ERROR_DIADLOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "We can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }*/

}

