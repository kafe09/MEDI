package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HausapothekeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hausapotheke);

        /* So sollte es sein laut Angabezettel, funktioniert aber nicht
        TextView tf_vorhanden = (TextView)findViewById(R.id.TextViewText);
        if (getIntent()!=null && getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            tf_vorhanden.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT)); }*/


        //haben wir vom Internet, geht aber auch nicht
        Bundle hpData = getIntent().getExtras();
        if(hpData == null) {
            return;
        }

       /* String hpMessage = hpData.getString("Hier sehen Sie Ihre Hausapotheke");
        final TextView tf_vorhanden =(TextView)findViewById(R.id.tf_vorhanden);
        tf_vorhanden.setText(hpMessage);*/
    }
}
