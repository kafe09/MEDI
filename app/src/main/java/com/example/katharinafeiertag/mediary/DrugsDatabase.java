package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.katharinafeiertag.mediary.Drugs;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DrugsDatabase extends SQLiteAssetHelper {

    private static final String DB_NAME="medikamenten.db";
    private static final int DB_VER=1;



    public DrugsDatabase(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    public List<Drugs> getDrug() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect ={"Pharmanummer","Handelsname","Mengenangabe","Mengenart","Kassenverkaufspreis","ATC-Code","BezeichnungATC-Code"};
        String tableName="drugs";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Drugs> result = new ArrayList<>();
        if(cursor.moveToFirst()) {

            do{
                Drugs drug = new Drugs();
                drug.setPharmaId(cursor.getString(cursor.getColumnIndex("Pharmanummer")));
                drug.setName(cursor.getString(cursor.getColumnIndex("Handelsname")));
                drug.setMenge(cursor.getString(cursor.getColumnIndex("Mengenangabe")));
                drug.setArt(cursor.getString(cursor.getColumnIndex("Mengenart")));
                drug.setPreis(cursor.getString(cursor.getColumnIndex("Kassenverkaufspreis")));
                drug.setCode(cursor.getString(cursor.getColumnIndex("ATC-Code")));
                drug.setBezeichnung(cursor.getString(cursor.getColumnIndex("BezeichnungATC-Code")));

                result.add(drug);

            }while (cursor.moveToNext());
        }return result;
    }

    public List<String> getNames() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect ={"Handelsname"};
        String tableName="drugs";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
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

        String [] sqlSelect ={"Pharmanummer","Handelsname","Mengenangabe","Mengenart","Kassenverkaufspreis","ATC-Code","BezeichnungATC-Code"};
        String tableName="drugs";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,"Handelsname LIKE ?",new String[]{"%"+name+"%"},null,null,null);
        List<Drugs> result = new ArrayList<>();
        if(cursor.moveToFirst()) {

            do{
                Drugs drug = new Drugs();
                drug.setPharmaId(cursor.getString(cursor.getColumnIndex("Pharmanummer")));
                drug.setName(cursor.getString(cursor.getColumnIndex("Handelsname")));
                drug.setMenge(cursor.getString(cursor.getColumnIndex("Mengenangabe")));
                drug.setArt(cursor.getString(cursor.getColumnIndex("Mengenart")));
                drug.setPreis(cursor.getString(cursor.getColumnIndex("Kassenverkaufspreis")));
                drug.setCode(cursor.getString(cursor.getColumnIndex("ATC-Code")));
                drug.setBezeichnung(cursor.getString(cursor.getColumnIndex("BezeichnungATC-Code")));

                result.add(drug);

            }while (cursor.moveToNext());
        }return result;
    }
}
