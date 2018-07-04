package com.example.katharinafeiertag.mediary;


import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.katharinafeiertag.mediary.R;

import java.io.IOException;

public class ViewMedikamente extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medikamente);

        ((Button) findViewById(R.id.button01)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelperMedi myDbHelper = new DatabaseHelperMedi(ViewMedikamente.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Toast.makeText(ViewMedikamente.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("medikamente", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Toast.makeText(ViewMedikamente.this,
                                "ID: " + c.getInt(0) + "\n" +
                                        "NAME: " + c.getString(1) + "\n" +
                                        "MENGE: " + c.getString(2) + "\n" +
                                        "ART: " + c.getString(3) + "\n" +
                                        "PHARMANUMMER:  " + c.getString(4),
                                Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());
                }
            }
        });

    }


}