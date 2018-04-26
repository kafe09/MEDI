package com.example.katharinafeiertag.mediary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MedikamentHinzufugenActivity extends AppCompatActivity {

    String value;
    private TextView tv;
    private ExampleBroadcastReciever resultReciever;
    ServiceDbAbfrage servicedbabfrage;
    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medikamenthinzufugen);
        tv = (TextView) findViewById(R.id.tv_anzeige);
        registerExampleBroadcastReceiver();
        start = (Button) findViewById(R.id.bt_show);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the function stops, the receiver needs to be unregistered
        unregisterReceiver(resultReciever);
    }

    public void anzeigen(View v) {

        int wartezeit;

    try{
        /*Toast toasti = Toast.makeText(MedikamentHinzufugenActivity.this, "Datenbank wird durchgesucht", Toast.LENGTH_SHORT);
        toasti.show();*/

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
}
