package com.example.katharinafeiertag.mediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.example.katharinafeiertag.mediary.Constant.FIRST_COLUMN;
import static com.example.katharinafeiertag.mediary.Constant.SECOND_COLUMN;
import static com.example.katharinafeiertag.mediary.Constant.THIRD_COLUMN;

//Hier sieht Benutzer all seine Medikamente in seiner Hausapotheke
public class alleMedEinsichtActivity extends AppCompatActivity {
    private static final String TAG = "alleMedEinsicht";
    ArrayList[] list = new ArrayList[3];
    ListView lview;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allemedeinsicht);
        Log.d(TAG, "alleMedEinsicht startet");

        lview = (ListView) findViewById(R.id.lv_medikamente);

    }
}