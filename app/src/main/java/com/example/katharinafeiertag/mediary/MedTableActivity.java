package com.example.katharinafeiertag.mediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MedTableActivity extends AppCompatActivity {
    TextView name1, menge1, art1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("msg", "in der onCreate von MedTableActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medtable);

       // TextView tv = (TextView) findViewById(R.id.name1);
        String übergabe = getIntent().getStringExtra("weitergabe");
      //  tv.setText(übergabe);

   /*     TextView name1 = (TextView) findViewById(R.id.name1);
        String userEingabeName = getIntent().getStringExtra("eingabeName");
        name1.setText(userEingabeName);
        Log.d("msg", "mitgegebener Name " + userEingabeName);

        TextView menge1 = (TextView) findViewById(R.id.menge1);
        String userEingabeMenge = getIntent().getStringExtra("eingabeMenge");
        menge1.setText(userEingabeMenge);

        TextView art1 = (TextView) findViewById(R.id.art1);
        String userEingabeArt = getIntent().getStringExtra("eingabeArt");
        menge1.setText(userEingabeArt);
*/

        }

    }

