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
    EditText id;
    Button btnUpdate;

    Context context = this;


    DatabaseOpenHelper drugsdatabase;
    SQLiteDatabase db;


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
        onAddClick();

        // SeekBar um die Menge der Medikamente festzulegen von 0 bis 500
        sb = (SeekBar) findViewById(R.id.seekBar);

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
    public void UpdateData() {

        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = drugsdatabase.updateData(id.getText().toString(),name.getText().toString(),menge.getText().toString(),art.getText().toString(),zulassungsnummer.getText().toString());
                        if(isUpdate==true)
                            Toast.makeText(MedikamentHinzufugenActivity.this,"Datenbank aktualisiert",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MedikamentHinzufugenActivity.this,"Datenbank nicht aktualisiert",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }*/
    public void onHinzufügen (View v) {
    Intent hpIntent = new Intent(this, HausapothekeMenuActivity.class);
    startActivity(hpIntent);
}


    public void onAddClick () {
        Button button = (Button) findViewById(R.id.bt_add);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drugsdatabase = new DatabaseOpenHelper(context);
                boolean isInserted = drugsdatabase.insertNewEntry(name.getText().toString(), menge.getText().toString(), art.getText().toString(), zulassungsnummer.getText().toString());
                drugsdatabase.close();

                if (isInserted == true) {
                    Toast temp = Toast.makeText(MedikamentHinzufugenActivity.this, "Medikament hinzugefügt", Toast.LENGTH_SHORT);
                    temp.show();
                } else {
                    Toast temp = Toast.makeText(MedikamentHinzufugenActivity.this, "Medikament nicht hinzugefügt", Toast.LENGTH_SHORT);
                    temp.show();
                }
                finish();
               onHinzufügen(null);
               //oder nix reinschreiben? 


            }

        });
    }
}
