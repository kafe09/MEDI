package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//in unserem Hausapothekenmenü der letzte Button - wird bei Stückanzahl an Tabletten reduziert
public class MedEinnahmeActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medeinnahme_activitiy);
    }

    //klick auf Einnahme-Button
    public void onEingenommenClick(View v) {
        Intent hpIntent = new Intent(this, HauptmenuActivity.class);
        startActivity(hpIntent);
    }
}
