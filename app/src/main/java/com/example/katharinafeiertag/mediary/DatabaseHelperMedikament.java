package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Datenbank für die Medikamente des Benutzers zu erstellen und Medikamente speichern

public class DatabaseHelperMedikament extends SQLiteOpenHelper {
    SearchAdapter sa = new SearchAdapter();
    public static final String TAG = DatabaseHelperContacts.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hausapotheke.db";
    private static final String TABLE_NAME = "medikamente";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HANDELSNAME = "handelsname";
    private static final String COLUMN_MENGENANGABE = "menge";
    private static final String COLUMN_MENGENART = "art";
    private static final String COLUMN_ZULASSUNGSNUMMER = "zulassungsnummer";
    SQLiteDatabase dbhausapotheke;

    private static final String TABLE_CREATE = "create table medikamente (id integer primary key not null ," +
            "handelsname text not null , menge text not null , art text not null ,zulassungsnummer text not null);";

    //Konstruktor
    public DatabaseHelperMedikament(Context context)
    //, String name, SQLiteDatabase.CursorFactory, int version)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.dbhausapotheke = db;
    }

    public void insertMedikament(MedikamentNeu mn) {
        dbhausapotheke = this.getWritableDatabase();

        ContentValues values2 = new ContentValues();
        //um die ID immer unique zu machen
        String query = "select * from contacts ";
        Cursor cursor = dbhausapotheke.rawQuery(query,null);
        int count = cursor.getCount();
        values2.put(COLUMN_ID, (count+1));       //+1 damit ID ermittelt werden kann

        values2.put(COLUMN_HANDELSNAME, mn.getHandelsname());
        values2.put(COLUMN_MENGENANGABE, mn.getMenge());
        values2.put(COLUMN_MENGENART, mn.getArt());
        values2.put(COLUMN_ZULASSUNGSNUMMER, mn.getZulassungsnummer());

        long id = dbhausapotheke.insert(TABLE_NAME,null,values2);
        dbhausapotheke.close();

        Log.d(TAG, "Medikament hinzugefügt" +id);
    }


    //Methode für LoginActivity um Email zu überprüfen von Datenbank
   /* public String searchMed(String handelsname) {
        dbhausapotheke = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = dbhausapotheke.rawQuery(query, null);
        String medi;   //Medikament

        if (cursor.moveToFirst()) {
            do {
                medi = cursor.getString(1);
                Log.d(TAG, "Medikament " +medi);

                if (medi.equals(sa.)) {             //wenn Benutzername gefunden wurde
                    b = cursor.getString(5);    //Passwort wird ermittelt
                    break;                         //Methode abbrechen, weil Benutzername+Passwort gefunden wurden
                }
            }
            while (cursor.moveToNext());
        }
        return b;                              //wenn kein Benutzer gefunden wurde
    }


    public String searchVorname(String uname) {
        dbhausapotheke = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = dbhausapotheke.rawQuery(query, null);
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
        dbhausapotheke = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = dbhausapotheke.rawQuery(query, null);
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
        dbhausapotheke = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = dbhausapotheke.rawQuery(query, null);
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
*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        dbhausapotheke.execSQL(query);
        this.onCreate(dbhausapotheke);
    }
}

