package com.example.katharinafeiertag.mediary;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View.OnClickListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.os.Handler;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


//Hier gibt der Benutzer das Medikament ein, welches er zu seiner persönlichen Hausapotheke hinzufügen will
public class MedikamentHinzufugenActivity extends AppCompatActivity {
    public static final String TAG = DatabaseHelperMedikament.class.getSimpleName();
    EditText name;
    TextView menge;
    EditText art;
    EditText zulassungsnummer;
    EditText id;
    Button btnUpdate;
   //  progress;

    Context context = this;

    //Thread
    private ExampleBroadcastReciever resultReciever;
    ServiceDbAbfrage servicedbabfrage;
    private TextView tv;


    DatabaseOpenHelper drugsdatabase;
    SQLiteDatabase db;

    //für Seekbar
    public SeekBar sb;
    int sbvalue;
    int sbmax = 250;
    int sbstart = 1;

    private ArrayList<HashMap> list;


    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "in onCreate von Medikament hinzufügen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medikamenthinzufugen);

        tv = (TextView) findViewById(R.id.tv_anzeige);
        registerExampleBroadcastReceiver();

        name = (EditText) findViewById(R.id.tf_handelsname);
        menge = (TextView) findViewById(R.id.tv_wert);
        art = (EditText) findViewById(R.id.tf_art);
        zulassungsnummer = (EditText) findViewById(R.id.tf_zulassungsnummer);

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
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }



    @Override
    protected void onStop() {
        super.onStop();
        // When the function stops, the receiver needs to be unregistered
        unregisterReceiver(resultReciever);

    }



    public void backtomenue () {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent2 = new Intent(getBaseContext(), HausapothekeMenuActivity.class);
                startActivity(intent2);
                //finish();
            }
        }, 6000);
    }


    public void onAddClick(View view) {
        int wartezeit;
        ProgressDialog progress = ProgressDialog.show(this, "Aktualisierung", "Warten Sie einen Moment...");

        try {
            wartezeit = 3000;
                    drugsdatabase = new DatabaseOpenHelper(context);
                    boolean isInserted = drugsdatabase.insertNewEntry(name.getText().toString(), menge.getText().toString(), art.getText().toString(), zulassungsnummer.getText().toString());
                    drugsdatabase.close();
                } catch (Exception e) {
            wartezeit = 0;
            Toast toasti2 = Toast.makeText(MedikamentHinzufugenActivity.this, "Fehlgeschlagen", Toast.LENGTH_LONG);
            toasti2.show();
        }

        Intent intent = new Intent(getBaseContext(), ServiceDbAbfrage.class);
        intent.putExtra("wartezeit", wartezeit);
        startService(intent);


        backtomenue();


    }


    private void registerExampleBroadcastReceiver(){
        // Create a broadcast receiver
        resultReciever = new ExampleBroadcastReciever();

        // Create an intent that indicates which broadcasts to listen for
        IntentFilter intentFilter = new IntentFilter();
        //intentFilter.addAction(ExampleIntentService.EXAMPLE_ACTION);
        intentFilter.addAction(ServiceDbAbfrage.EXAMPLE_ACTION);

        // Register the receiver
        registerReceiver(resultReciever, intentFilter);
    }


    private class ExampleBroadcastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String ex_info = intent.getStringExtra(ServiceDbAbfrage.EXAMPLE_CATEGORY);
            tv.setText(ex_info);
        }
    }
}


