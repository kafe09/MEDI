package com.example.katharinafeiertag.mediary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.widget.AdapterView.OnItemSelectedListener;


//Hier gibt der Benutzer das Medikament ein, welches er zu seiner persönlichen Hausapotheke hinzufügen will
public class MedikamentHinzufugenActivity extends AppCompatActivity {

    DatabaseHelperMedikament helper = new DatabaseHelperMedikament(this);
    EditText handelsname, zulassungsnummer;
    TextView menge;
    EditText art;

    //für Seekbar
    public SeekBar sb;
    public TextView tv1;

    int sbvalue;
    int sbmax = 500;
    int sbstart = 1;

    //auch für IntentService - siehe unten
/*
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    String value;
    private TextView tv;
    private ExampleBroadcastReciever resultReciever;
    ServiceDbAbfrage servicedbabfrage;
    Button start;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medikamenthinzufugen);
       /* tv = (TextView) findViewById(R.id.tv_anzeige);
        registerExampleBroadcastReceiver();
        start = (Button) findViewById(R.id.bt_show);*/


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
    }



    //alles für IntentService; Java UI Thread Service anders lösen!!!!


    /*public void anzeigen(View v) {


        int wartezeit;

    try{
        *//*Toast toasti = Toast.makeText(MedikamentHinzufugenActivity.this, "Datenbank wird durchgesucht", Toast.LENGTH_SHORT);
        toasti.show();*//*

        wartezeit = 5000;
        AssetManager am = getAssets();
        InputStream is = am.open("versuch.xls");
        Workbook wb = Workbook.getWorkbook(is);
        Sheet s = wb.getSheet(0);
        int row = s.getRows();
        int col = s.getColumns();
        String xx="";

        for(int i=0;i<row;i++)
        {
            for(int c=0;c<col;c++)
            {
                Cell z = s.getCell(c,i);
                xx =xx+z.getContents();
            }
            xx=xx+"\n";
        }
        display(xx);
    }
    catch(Exception e){
        wartezeit = 0;
        Toast toasti2 = Toast.makeText(MedikamentHinzufugenActivity.this,"Fehlgeschlagen",Toast.LENGTH_SHORT);
        toasti2.show();
    }

        Intent intent = new Intent(getBaseContext(),ServiceDbAbfrage.class);
        intent.putExtra("wartezeit",wartezeit);
        startService(intent);

    }

    public void display(String value) {
        TextView example = (TextView) findViewById(R.id.tv_versuch);
        example.setMovementMethod(new ScrollingMovementMethod());
        example.setText(value);
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

*/
    //war für IntentService
    /*@Override
    protected void onStop() {
        super.onStop();
        // When the function stops, the receiver needs to be unregistered
        unregisterReceiver(resultReciever);
    }*/



}
