package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    DatabaseOpenHelper openHelper;
    DatabaseHelperContacts helperC;
    Button button;

    Context context = this;
    String name,userName, art, menge, nummer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allemedeinsicht);
        Log.d(TAG, "alleMedEinsicht startet");

        lview = (ListView) findViewById(R.id.lv_medikamente);
        button = (Button) findViewById(R.id.button2);
        name = getIntent().getStringExtra("name");

        openHelper= new DatabaseOpenHelper(this);
        openHelper.getDrugsByNameForHome(name);

        SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);

        userName = preferences.getString("displayName", "");  //ist der Username der gerade eingeloggt ist
        Contact c = new DatabaseHelperContacts(this).GetContactByUserName(userName);
        Log.d(TAG,"benutzer eingeloggt: " +userName);

        helperC = new DatabaseHelperContacts(this);
        helperC.getUserID(userName);

        art = openHelper.getDrugsArtByName(name);
        menge = openHelper.getDrugsAmountByName(name);
        nummer = openHelper.getDrugsNummerByName(name);

        openHelper = new DatabaseOpenHelper(context);
        boolean isInserted = openHelper.insertNewEntryHomeKit (userName, name, menge, art ,nummer);
        openHelper.close();

        Log.d("msg","Daten"+ art + " " +menge + " "+ nummer);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(alleMedEinsichtActivity.this, ListViewActivity.class);
                startActivity(i);
            }


    });




    }


}