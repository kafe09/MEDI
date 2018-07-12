package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//um die Datenbank für verschiedene Benutzer zu erstellen
public class DatabaseHelperContacts extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelperContacts.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "medikamente.db";
    //contacts Datenbank
    private static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VORNAME = "vorname";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_UNAME = "uname";
    public static final String COLUMN_PASSWORT = "passwort";
    public static final String COLUMN_FINGERPRINTENABLED = "fingerprint";
    //Hausapotheke Datenbank
    /*private static final String TABLE_NAME3 = "Hausapotheke";
    private static final String UserID = COLUMN_ID;*/

    /*private static final String selectQuery = "SELECT MedID FROM Medikamente";
    //Cursor cursor = db.rawQuery(selectQuery, null);
    private static final String MedikamentID = selectQuery;*/

    //Contacts
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null ," +
            "vorname text not null , " +
            "name text not null , " +
            "email text not null , " +
            "uname text not null , " +
            "passwort text not null, " +
            "fingerprint text not null default 'false'," +
            "public_key text " +
            ");";

   /* //Hausapotheke
    private static final String TABLE_CREATE2 = "create table Hausapotheke (UserID integer primary key not null," +
            "MedikamentID integer not null);";
*/
    //Konstruktor
    public DatabaseHelperContacts(Context context)
            // String name, SQLiteDatabase.CursorFactory, int version)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        //db.execSQL(TABLE_CREATE2);
        this.db = db;
    }

    /***
     * Insert or updates a contact (it's idempotent)
     * @param c The Contact to be updated
     */
    public void insertContact(Contact c) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //um die ID immer unique zu machen
        String query = "select "+ COLUMN_ID+ " from contacts order by " + COLUMN_ID + " desc";
        Cursor cursor = db.rawQuery(query,null);
        Log.d(TAG, "Found an entry in database: " + cursor.getCount());
        int count = (cursor.getCount() > 0 && cursor.moveToFirst()) ? Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))) : 0;

        cursor.close();
        //hier entsteht ein doofer kommentar weil kathi foto macht xD

        query = "select " + COLUMN_ID + "  from contacts where uname = '" + c.getUname() + "'";
         cursor = db.rawQuery(query,null);

        values.put(COLUMN_VORNAME, c.getVorname());
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASSWORT, c.getPasswort());
        values.put(COLUMN_FINGERPRINTENABLED, c.getFingerPrintenabled());

        long id;

         if(! cursor.moveToFirst()) {
             values.put(COLUMN_ID, (count + 1));       //+1 damit ID ermittelt werden kann (0 ist kein Benutzer)
             id = db.insert(TABLE_NAME, null, values);
         } else {
             Log.d(TAG, "DB: found contact, updating user "+ COLUMN_ID + "=" + cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
             id = db.update(TABLE_NAME,  values, COLUMN_ID + "=" + cursor.getString(cursor.getColumnIndex(COLUMN_ID)), null);
         }
        db.close();

        Log.d(TAG, "Benutzer hinzugefügt " +id);
    }

    public Contact GetContactByUserName(String uname) {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COLUMN_UNAME + " = '" + uname + "'";
        Cursor cursor = db.rawQuery(query, null);
        String a;   //Benutzername
        String b;   //Passwort
        b = "Sie sind noch nicht registriert";

        if (cursor.moveToFirst())
            return new Contact(cursor);
        else
            Log.d(TAG, "No user with username '" + uname + "' found");

        return null;
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


    public String getUserID (String uname) {
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a;   //Benutzername
        String b;   //ID
        b = " ";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(4);        //Benutzernamen werden durchiteriert

                if (a.equals(uname)) {             //wenn Benutzername gefunden wurde
                    b = cursor.getString(1);    //ID wird ermittelt
                    break;                         //Methode abbrechen, weil Benutzername+ID gefunden wurden
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
        //String query2 = "DROP TABLE IF EXISTS " +TABLE_NAME3;
        //db.execSQL(query2);
        this.onCreate(db);
    }
}
