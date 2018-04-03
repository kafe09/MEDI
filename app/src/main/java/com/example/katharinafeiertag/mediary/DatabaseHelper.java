package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by scheerbernhard on 30.03.18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VORNAME = "vorname";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORT = "passwort";
    //private static final String COLUMN_GESCHLECHT = "geschlecht";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null ," +
            "vorname text not null , name text not null , email text not null , passwort text not null);";

    //Konstruktor
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertContact(Contact c) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // um die ID immer unique zu machen
        String query = "select * from contacts ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);

        values.put(COLUMN_VORNAME, c.getVorname());
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASSWORT, c.getPasswort());
        //values.put(COLUMN_GESCHLECHT)

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //Methode für LoginActivity um Email zu überprüfen von Datenbank

    public String searchPass(String name) {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(name)) {
                    b = cursor.getString(2);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
            return b;
        }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
