package com.example.katharinafeiertag.mediary;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.HashMap;



//Hier gibt der Benutzer das Medikament ein, welches er zu seiner persönlichen Hausapotheke hinzufügen will
public class MedikamentHinzufugenActivity extends AppCompatActivity {
    public static final String TAG = DatabaseHelperMedikament.class.getSimpleName();
    EditText name;
    TextView menge;
    EditText art;
    EditText zulassungsnummer;

    Context context = this;


    DrugsDatabase drugsdatabase;
    SQLiteDatabase db;



    String neuName, neuMengenart, neuLagerbestandString, neuZulassungsnummerString;
    int neuLagerbestandInt, neuZulassungsnummerInt;

    //für Seekbar
    public SeekBar sb;
    int sbvalue;
    int sbmax = 250;
    int sbstart = 1;

    private ArrayList<HashMap> list;




    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"in onCreate von Medikament hinzufügen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medikamenthinzufugen);

        name = (EditText) findViewById(R.id.tf_handelsname);
        menge = (TextView) findViewById(R.id.tv_wert);
        art = (EditText) findViewById(R.id.tf_art);
        zulassungsnummer = (EditText) findViewById(R.id.tf_zulassungsnummer);

        // SeekBar um die Menge der Medikamente festzulegen von 0 bis 500
        sb = (SeekBar) findViewById(R.id.seekBar);
        menge = (TextView) findViewById(R.id.tv_wert);

        sb.setMax(sbmax);
        sb.setProgress(sbstart);
        menge.setText(Integer.toString(sbstart));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbvalue = sb.getProgress();
                menge.setText(Integer.toString(sbvalue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // SeekBar um die Menge der Medikamente festzulegen von 0 bis 500
        sb = (SeekBar) findViewById(R.id.seekBar);
        menge = (TextView) findViewById(R.id.tv_wert);

        sb.setMax(sbmax);
        sb.setProgress(sbstart);
        menge.setText(Integer.toString(sbstart));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbvalue = sb.getProgress();
                menge.setText(Integer.toString(sbvalue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

/*
    public void addContact(View view) {

        String name_med = name.getText().toString();
        String menge_med = menge.getText().toString();
        String art_med = art.getText().toString();
        String nummer_med = zulassungsnummer.getText().toString();
        drugsdatabase = new DrugsDatabase(context);
        db = drugsdatabase.getWritableDatabase();
        drugsdatabase.insertNewEntry(name_med,menge_med,art_med,nummer_med,db);
        Toast.makeText(getBaseContext(),"Eintrag gespeichert",Toast.LENGTH_LONG).show();
         drugsdatabase.close();
    }
*/


    public void onAddClick (View v) {
        Button button = (Button) findViewById(R.id.bt_add);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),DrugsDatabase.class);

                EditText handelsname = (EditText) findViewById(R.id.tf_handelsname);
                neuName = handelsname.getText().toString();
                intent.putExtra("eingabeName", neuName);

                menge = (TextView) findViewById(R.id.tv_wert);
                neuLagerbestandString = menge.getText().toString();
                neuLagerbestandInt = Integer.parseInt(neuLagerbestandString);
                intent.putExtra("eingabeBestand", neuLagerbestandInt);

                art = (EditText) findViewById(R.id.tf_art);
                neuMengenart = art.getText().toString();
                intent.putExtra("eingabeArt", neuMengenart);

                //daweil noch nicht in der Anzeige von allen Medikamenten sichtbar
                zulassungsnummer = (EditText) findViewById(R.id.tf_zulassungsnummer);
                neuZulassungsnummerString = zulassungsnummer.getText().toString();
                neuZulassungsnummerInt = Integer.parseInt(neuZulassungsnummerString.toString());
                intent.putExtra("eingabeNummer", neuZulassungsnummerInt);

                Log.d("msg","Infos des neu hinzugefügten Medikaments: " +neuName +" " +neuLagerbestandInt +" "+neuMengenart + " " +neuZulassungsnummerInt);



                startActivityForResult(intent, 1);
            }
        });
    }
}

