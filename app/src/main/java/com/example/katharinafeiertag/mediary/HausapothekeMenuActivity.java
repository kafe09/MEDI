package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HausapothekeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hausapothekemenu);
    }

    //klick auf alleAnsehen-Button
    public void onAlleClick(View v) {
        Intent hpIntent = new Intent(this, MedTableActivity.class);
        startActivity(hpIntent);
    }

    //klick auf hinzufügen-Button
    public void onHinzufügenClick(View v) {
        Intent hpIntent = new Intent(this, MedSucheActivity.class);
        startActivity(hpIntent);
    }

    //klick auf Einnahme-Button
    public void onEinnahmeClick(View v) {
     Intent hpIntent = new Intent(this, MedTableActivity.class);
        startActivity(hpIntent);
    }
}
