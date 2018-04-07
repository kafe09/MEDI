package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VORNAME = "vorname";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASSWORT = "passwort";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null ," +
            "vorname text not null , name text not null , email text not null , uname text not null , passwort text not null);";

    //Konstruktor
    public DatabaseHelper(Context context)
            //, String name, SQLiteDatabase.CursorFactory, int version)
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
        //um die ID immer unique zu machen
        String query = "select * from contacts ";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, (count+1));       //+1 damit ID ermittelt werden kann (0 ist kein Benutzer)

        values.put(COLUMN_VORNAME, c.getVorname());
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASSWORT, c.getPasswort());

        long id = db.insert(TABLE_NAME,null,values);
        db.close();

        Log.d(TAG, "Benutzer hinzugefügt" +id);
    }


    //Methode für LoginActivity um Email zu überprüfen von Datenbank
    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a;   //Benutzername
        String b;   //Passwort
        b = "Sie sind noch nicht registriert";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(4);        //Benutzernamen werden durchiteriert

                if (a.equals(uname)) {             //wenn Benutzername gefunden wurde
                    b = cursor.getString(5);    //Passwort wird ermittelt
                    break;                         //Methode abbrechen, weil Benutzername+Passwort gefunden wurden
                }
            }
            while (cursor.moveToNext());
        }
            return b;                              //wenn kein Benutzer gefunden wurde
        }


        public String searchVorname(String uname) {
            db = this.getReadableDatabase();
            String query = "select * from " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            String a;   //Benutzername
            String b;   //Vorname
            b = " ";

            if (cursor.moveToFirst()) {
                do {
                    a = cursor.getString(4);        //Benutzernamen werden durchiteriert

                    if (a.equals(uname)) {             //wenn Benutzername gefunden wurde
                        b = cursor.getString(1);    //Vorname wird ermittelt
                        break;                         //Methode abbrechen, weil Benutzername+Vorname gefunden wurden
                    }
                }
                while (cursor.moveToNext());
            }
            return b;                              //wenn kein Benutzer gefunden wurde
        }

    public String searchNachname(String uname) {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a;   //Benutzername
        String b;   //Passwort
        b = " ";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(4);        //Benutzernamen werden durchiteriert

                if (a.equals(uname)) {             //wenn Benutzername gefunden wurde
                    b = cursor.getString(2);    //Nachname wird ermittelt
                    break;                         //Methode abbrechen, weil Benutzername+Nachname gefunden wurden
                }
            }
            while (cursor.moveToNext());
        }
        return b;                              //wenn kein Benutzer gefunden wurde
    }

    public String searchEmail (String uname) {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a;   //Benutzername
        String b;   //EMail
        b = " ";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(4);        //Benutzernamen werden durchiteriert

                if (a.equals(uname)) {             //wenn Benutzername gefunden wurde
                    b = cursor.getString(3);    //EMail wird ermittelt
                    break;                         //Methode abbrechen, weil Benutzername+EMail gefunden wurden
                }
            }
            while (cursor.moveToNext());
        }
        return b;                              //wenn kein Benutzer gefunden wurde
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
