package com.example.katharinafeiertag.mediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class alleMedEinsichtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allemedeinsicht);


        //haben wir vom Internet, geht aber auch nicht
        Bundle hpData = getIntent().getExtras();
        if(hpData == null) {
            return;
        }

    }
}
