package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

//Datenbank für unsere Medikamenten-DB mit über 7000 Medikamenten
public class DrugsDatabase extends SQLiteAssetHelper {


    private static final String DB_NAME= "medikamente.db";
    private static final int DB_VER= 1;

    private static final String TABLE = "Medikamente";

    public static final String ID = "MedID";
    public static final String NAME = "Handelsname";
    public static final String MENGE = "Mengenangabe";
    public static final String ART = "Mengenart";
    public static final String NUMMER = "Pharmanummer";


    public DrugsDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }


    public void insertNewEntry (String name, String mengenangabe, String mengenart, String pharmanummer, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        //contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(MENGE,mengenangabe);
        contentValues.put(ART,mengenart);
        contentValues.put(NUMMER,pharmanummer);

        db.insert(TABLE,null,contentValues);
        Log.d("key","Database - one row is inserted.....");
    }



    public List<Drugs> getDrug() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Log.d("in der DrugDatabases", "SQLiteQueryBuilder successful");


        //Kathi habe hier: "ATCCode", "BezeichnungATCCode" gelöscht
        String [] sqlSelect = {"MedID", "Handelsname", "Mengenangabe", "Mengenart", "Pharmanummer"};
        String tableName = "Medikamente";
        Log.d("in der DrugDatabases", " successful" + sqlSelect);

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Drugs> result = new ArrayList<>();
        if(cursor.moveToFirst()) {

            do{
                Drugs drug = new Drugs();
                drug.setMedID(cursor.getInt(cursor.getColumnIndex("MedID")));
                drug.setName(cursor.getString(cursor.getColumnIndex("Handelsname")));
                drug.setMenge(cursor.getString(cursor.getColumnIndex("Mengenangabe")));
                drug.setArt(cursor.getString(cursor.getColumnIndex("Mengenart")));
                drug.setNummer(cursor.getString(cursor.getColumnIndex("Pharmanummer")));

                result.add(drug);

            }while (cursor.moveToNext());
        }return result;
    }

    public List<String> getNames() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Handelsname"};
        String tableName = "Medikamente";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()) {

            do{
                result.add(cursor.getString(cursor.getColumnIndex("Handelsname")));
            }while (cursor.moveToNext());
        }return result;
    }

    public List<Drugs> getDrugsByName(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"MedID" ,"Handelsname" ,"Mengenangabe" ,"Mengenart" ,"Pharmanummer"};
        String tableName = "Medikamente";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "Handelsname LIKE ?",new String[]{"%"+name+"%"}, null, null, null);
        List<Drugs> result = new ArrayList<>();
        if(cursor.moveToFirst()) {

            do{
                Drugs drug = new Drugs();
                drug.setMedID(cursor.getInt(cursor.getColumnIndex("MedID")));
                drug.setName(cursor.getString(cursor.getColumnIndex("Handelsname")));
                drug.setMenge(cursor.getString(cursor.getColumnIndex("Mengenangabe")));
                drug.setArt(cursor.getString(cursor.getColumnIndex("Mengenart")));
                drug.setNummer(cursor.getString(cursor.getColumnIndex("Pharmanummer")));

                result.add(drug);

            }while (cursor.moveToNext());
        }return result;
    }
}
