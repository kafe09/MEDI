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
    public static final String TAG = DatabaseHelperMedikament.class.getSimpleName();
    private static final int DATABASE_VERSION = 15;
    private static final String DATABASE_NAME = "medikamenten.db";
    private static final String TABLE_NAME = "medikamente";
    private static final String COLUMN_ID = "MedID";
    private static final String COLUMN_HANDELSNAME = "Handelsname";
    private static final String COLUMN_MENGENANGABE = "Mengenangabe";
    private static final String COLUMN_MENGENART = "Mengenart";
    private static final String COLUMN_NUMMER = "Pharmanummer";
    SQLiteDatabase dbmedikamente;

    private static final String TABLE_CREATE = "create table medikamente (MedID text not null primary key ," +
            "Handelsname text not null , Mengenangabe integer not null , Mengenart text not null ,Pharmanummer integer not null unique);";

    //Konstruktor
    public DatabaseHelperMedikament(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    //halloWelt

    @Override
    public void onCreate(SQLiteDatabase dbmedikamente) {
        dbmedikamente.execSQL(TABLE_CREATE);
   /*     Log.d(TAG, "in der onCreate von Databasehelper Medikamente");
        dbmedikamente.execSQL("CREATE TABLE " + TABLE_NAME + " ("
        + COLUMN_ID + " TEXT, "
        + COLUMN_HANDELSNAME + " TEXT, "
        + COLUMN_MENGENANGABE + " INTEGER, "
        + COLUMN_MENGENART + " TEXT, "
        + COLUMN_NUMMER + " INTEGER );");
        this.dbmedikamente = dbmedikamente;
        Log.d(TAG, "Create Table successful");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbmedikamente, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " +TABLE_NAME;
        dbmedikamente.execSQL(query);
        this.onCreate(dbmedikamente);
    }


public void insertMedikament(Drugs mn) {
    dbmedikamente = this.getWritableDatabase();

        ContentValues values2 = new ContentValues();
        //um die ID immer unique zu machen
        String query = "select * from medikamente ";
        Cursor cursor = dbmedikamente.rawQuery(query,null);
        int count = cursor.getCount();
        values2.put(COLUMN_ID, (count+1));       //+1 damit ID ermittelt werden kann

        values2.put(COLUMN_HANDELSNAME, mn.getName());
        values2.put(COLUMN_MENGENANGABE, mn.getMenge());
        values2.put(COLUMN_MENGENART, mn.getArt());
        values2.put(COLUMN_NUMMER, mn.getNummer());

        long id = dbmedikamente.insert(TABLE_NAME,null,values2);
    dbmedikamente.close();

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


}

