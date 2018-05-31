package com.example.katharinafeiertag.mediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.katharinafeiertag.mediary.Constant.FIRST_COLUMN;
import static com.example.katharinafeiertag.mediary.Constant.SECOND_COLUMN;
import static com.example.katharinafeiertag.mediary.Constant.THIRD_COLUMN;

//Hier sieht Benutzer all seine Medikamente in seiner Hausapotheke
public class alleMedEinsichtActivity extends AppCompatActivity {
    private ArrayList<HashMap> list;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allemedeinsicht);

        ListView lview = (ListView) findViewById(R.id.lv_medikamente);
        populateList();
        ListViewAdapter adapter = new ListViewAdapter(this, list);
        lview.setAdapter(adapter);
    }

    private void populateList() {
        list = new ArrayList<HashMap>();

        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,"Versuch_Name");
        temp.put(SECOND_COLUMN, "Versuch_Menge");
        temp.put(THIRD_COLUMN, "Versuch_Mengenart");
        list.add(temp);
    }
}
