package com.example.katharinafeiertag.mediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HausapothekeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hausapotheke);

        Bundle hpData = getIntent().getExtras();
        if(hpData == null) {
            return;
        }
        String hpMessage = hpData.getString("Hier sehen Sie Ihre Hausapotheke");
        final TextView tf_vorhanden =(TextView)findViewById(R.id.tf_vorhanden);
        tf_vorhanden.setText(hpMessage);
    }
}
