package com.example.katharinafeiertag.mediary;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by katharinafeiertag on 11.07.18.
 */

public class ListViewActivity extends Activity {
    DatabaseOpenHelper openHelper;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> Menge_ArrayList = new ArrayList<String>();
    ArrayList<String> Art_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allemedeinsicht);
        Log.d("im ListViewActivity", " ListViewActivity aufgerufen");

        LISTVIEW = (ListView) findViewById(R.id.lv_medikamente);

        openHelper = new DatabaseOpenHelper(this);
    }

    @Override
    protected void onResume () {
        ShowSQLiteDBdata();
        super.onResume();
    }


    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = openHelper.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM Hausapotheke", null);


        NAME_ArrayList.clear();
        Menge_ArrayList.clear();
        Art_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.haName)));

                Menge_ArrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.haMenge)));

                Art_ArrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.haArt)));

            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteListAdapter(ListViewActivity.this, NAME_ArrayList, Menge_ArrayList, Art_ArrayList);

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
}




