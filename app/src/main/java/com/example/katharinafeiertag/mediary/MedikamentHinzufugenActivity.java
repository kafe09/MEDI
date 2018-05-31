package com.example.katharinafeiertag.mediary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.widget.AdapterView.OnItemSelectedListener;


//Hier gibt der Benutzer das Medikament ein, welches er zu seiner persönlichen Hausapotheke hinzufügen will
public class MedikamentHinzufugenActivity extends AppCompatActivity {
    public static final String TAG = DatabaseHelperContacts.class.getSimpleName();
    EditText handelsname, zulassungsnummer;
    TextView menge;
    EditText art;

    String neuName, neuMengenart, neuLagerbestandString, neuZulassungsnummerString;
    int neuLagerbestandInt, neuZulassungsnummerInt;

    //für Seekbar
    public SeekBar sb;
    public TextView tv1;
    int sbvalue;
    int sbmax = 250;
    int sbstart = 1;

    private ArrayList<HashMap> list;


    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"in onCreate von Medikament hinzufügen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medikamenthinzufugen);

        handelsname = (EditText) findViewById(R.id.tf_handelsname);
        neuName = handelsname.toString();

        menge = (TextView) findViewById(R.id.tv_wert);
        neuLagerbestandString = menge.toString();
        neuLagerbestandInt = Integer.parseInt(neuLagerbestandString);

        art = (EditText) findViewById(R.id.tf_art);
        neuMengenart = art.toString();

        //daweil noch nicht in der Anzeige von allen Medikamenten sichtbar
        zulassungsnummer = (EditText) findViewById(R.id.tf_zulassungsnummer);
        neuZulassungsnummerString = zulassungsnummer.toString();
        neuZulassungsnummerInt = Integer.parseInt(neuZulassungsnummerString.toString());

        Log.d(TAG,"Infos des neu hinzugefügten Medikaments: " +neuName +" " +neuLagerbestandInt +" "+neuMengenart + " " +neuZulassungsnummerInt);

        // SeekBar um die Menge der Medikamente festzulegen von 0 bis 500
        sb = (SeekBar) findViewById(R.id.seekBar);
        tv1 = (TextView) findViewById(R.id.tv_wert);

        sb.setMax(sbmax);
        sb.setProgress(sbstart);
        tv1.setText(Integer.toString(sbstart));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbvalue = sb.getProgress();
                tv1.setText(Integer.toString(sbvalue));
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
        tv1 = (TextView) findViewById(R.id.tv_wert);

        sb.setMax(sbmax);
        sb.setProgress(sbstart);
        tv1.setText(Integer.toString(sbstart));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbvalue = sb.getProgress();
                tv1.setText(Integer.toString(sbvalue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onAddClick (View v) {
        Intent intent = new Intent (getBaseContext(),alleMedEinsichtActivity.class);
        startActivity(intent);



    }
}


//DatabaseHelperMedikament helper = new DatabaseHelperMedikament(this);


//Anfang Medikamente der Hausapotheke in Datenbank zu speicher
    /*//für Seekbar
    public SeekBar sb;
    public TextView tv1;

    int sbvalue;
    int sbmax = 500;
    int sbstart = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medikamenthinzufugen);
       *//* tv = (TextView) findViewById(R.id.tv_anzeige);
        registerExampleBroadcastReceiver();
        start = (Button) findViewById(R.id.bt_show);*//*


        handelsname = (EditText) findViewById(R.id.tf_handelsname);
        menge = (TextView) findViewById(R.id.tv_wert);
        art = (EditText) findViewById(R.id.tf_art);
        zulassungsnummer = (EditText) findViewById(R.id.tf_zulassungsnummer);

        helper = new DatabaseHelperMedikament(this);


        //Spinner um die Mengenart der Medikamente auszuwählen
        final Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this,R.array.spinneritems,android.R.layout.simple_list_item_1);

        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adp);
        sp.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                String ss = sp.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), ss, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // SeekBar um die Menge der Medikamente festzulegen von 0 bis 500
        sb = (SeekBar) findViewById(R.id.seekBar);
        tv1 = (TextView) findViewById(R.id.tv_wert);

        sb.setMax(sbmax);
        sb.setProgress(sbstart);
        tv1.setText(Integer.toString(sbstart));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbvalue = sb.getProgress();
                tv1.setText(Integer.toString(sbvalue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    //Intent um neues Medikament zur Datenbank hinzuzufügen
    public void onAddClick (View v) {
        Intent intent = new Intent(getBaseContext(), alleMedEinsichtActivity.class );
        startActivity(intent);

        String handelsnamestr = handelsname.getText().toString();
        String mengestr = menge.getText().toString();   //eigentlich wäre hier getText() - is rot
        String artstr = art.getText().toString();
        Log.d("daten menge danach art", mengestr + artstr);
        String zulassungsnummerstr = zulassungsnummer.getText().toString();


        if (handelsnamestr.isEmpty() && mengestr.isEmpty() && artstr.isEmpty()){
            displayToast ("Bitte füllen Sie Handelsname, Mengenangabe und Mengenart aus!.");
        }
        else {
            //insert details in database
            MedikamentNeu mn = new MedikamentNeu();
            mn.setHandelsname(handelsnamestr);
            mn.setMenge(mengestr);
            mn.setArt(artstr);
            mn.setZulassungsnummer(zulassungsnummerstr);
            helper.insertMedikament(mn);
        }
    }

    public void displayToast (String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }*/
