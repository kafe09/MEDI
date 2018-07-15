package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


//Hier sieht Benutzer all seine Medikamente in seiner Hausapotheke
public class alleMedEinsichtActivity extends AppCompatActivity {
    private static final String TAG = "alleMedEinsicht";


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapterdigital adapter;
    TextView textView;
    DatabaseHelperContacts helperC;
    SQLiteDatabase db;

    DatabaseOpenHelper openHelper;
    Context context = this;
    String name,userName, art, menge, nummer;
    String id;
    int idint;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allemedeinsicht);
        Log.d(TAG, "alleMedEinsicht startet");
        name = getIntent().getStringExtra("name");

        openHelper= new DatabaseOpenHelper(this);
        openHelper.getDrugsByNameForHome(name);

        SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);

        userName = preferences.getString("displayName", "");  //ist der Username der gerade eingeloggt ist
        Contact c = new DatabaseHelperContacts(this).GetContactByUserName(userName);
        Log.d(TAG,"benutzer eingeloggt: " +userName);

        helperC = new DatabaseHelperContacts(this);
        id = helperC.getUserID(userName);
        idint = Integer.parseInt(id);
        Log.d(TAG,"user id gefunden "+idint);

        //openHelper.selectUser(id);
        //SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        art = openHelper.getDrugsArtByName(name);
        menge = openHelper.getDrugsAmountByName(name);
        nummer = openHelper.getDrugsNummerByName(name);

        openHelper = new DatabaseOpenHelper(context);
        boolean isInserted = openHelper.insertNewEntryHomeKit (userName, name, menge, art ,nummer);
        openHelper.close();

        Log.d("msg","Daten"+ art + " " +menge + " "+ nummer);

        recyclerView = (RecyclerView) findViewById(R.id.recyler_alleMed);

        recyclerView.addOnItemTouchListener(new ViewCardListener(context, recyclerView, new ViewCardListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        AlertDialog.Builder a_builder = new AlertDialog.Builder(alleMedEinsichtActivity.this);
                        a_builder.setMessage("Wollen Sie das Medikament jetzt einnehmen?")
                                .setCancelable(false)
                                .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i = new Intent(getBaseContext(),HauptmenuActivity.class);
                                        startActivity(i);
                                        Toast temp = Toast.makeText(alleMedEinsichtActivity.this, "Medikamentenbestand wurde verringert", Toast.LENGTH_LONG);
                                        temp.show();

                                        finish();
                                    }
                                })
                                .setNegativeButton("ABBRECHEN", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Alert !!!");
                        alert.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

            Log.d("vor adapter", "apdater beginnt");


        adapter = new SearchAdapterdigital(this,openHelper.getmeineMed(idint));
        Log.d("werte mitbekommen ", "Daten" + openHelper.getmeineMed(idint));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new ViewCardListener(getBaseContext(), recyclerView, new ViewCardListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }






}