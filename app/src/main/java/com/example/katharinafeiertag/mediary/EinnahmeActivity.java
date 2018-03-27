package com.example.katharinafeiertag.mediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.TextView;

public class EinnahmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einnahme);

        final String[] tage = getResources().getStringArray(R.array.wochentage);
        GridView gridview = (GridView) findViewById(R.id.gv);

        ArrayAdapter<String> countryAdapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tage);
        gridview.setAdapter(countryAdapter);

        //Listener
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                String s = ((TextView) view).getText() + " " + position;
                Toast.makeText(EinnahmeActivity.this, tage[position], Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClickClose(View view) {
        finish();
    }
}
