package com.example.katharinafeiertag.mediary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HauptmenuActivity extends AppCompatActivity {
    //Aufgabenstellung: Ein Ergebnis aus einer Aktivität zurückgeben
    public static final String REQUEST_RESULT = "REQUEST_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenu);
    }


    //klick auf Apotheken-Button funktioniert
    public void onApothekenClick(View v) {
        //launchIntent Methode der Aufgabenstellung
        //Art: Action_View: hier wird die Google Maps App geöffnet
        Intent apothekenIntent = new Intent(Intent.ACTION_VIEW);
        apothekenIntent.setData(Uri.parse("https://www.google.com/maps"));
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
        EditText editText = (EditText) findViewById(R.id.editText);
        String text = editText.getText().toString();
        Intent einnahmeIntent = new Intent(this, EinnahmeActivity.class);
        einnahmeIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivityForResult(einnahmeIntent, 1);
    }

    //klick auf Hausapotheke-Button
    public void onHausapothekeClick(View v) {
        //Aufgabenstellung: "Daten an eine andere Aktivität übergeben"
        final EditText tf_editText = (EditText) findViewById(R.id.tf_editText);
        String userMessage = tf_editText.getText().toString();
        Intent hpIntent = new Intent(this, HausapothekeActivity.class);
        // hpIntent.putExtra("Hier sehen Sie Ihre Hausapotheke", userMessage);
        hpIntent.putExtra(Intent.EXTRA_TEXT, userMessage);
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