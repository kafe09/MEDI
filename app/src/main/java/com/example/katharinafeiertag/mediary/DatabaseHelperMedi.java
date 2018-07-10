package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelperMedi extends SQLiteOpenHelper {

    public SQLiteDatabase DB;
    public String DBPath;
    public static String DBName = "medica";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "medikamente";

    public DatabaseHelperMedi(Context context) {
        super(context, DBName, null, version);
        currentContext = context;
        DBPath = "/data/data/" + context.getPackageName() + "/databases";
        createDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }

    private void createDatabase() {
        boolean dbExists = checkDbExists();

        if (dbExists) {
// do nothing
        } else {
            DB = currentContext.openOrCreateDatabase(DBName, 0, null);
            DB.execSQL("CREATE TABLE IF NOT EXISTS " +
                    tableName +
                    " (MedID INT(10), Handelsname VARCHAR," +
                    " Mengenangabe VARCHAR, Mengenart VARCHAR," +
                    " Pharmanummer VARCHAR);");

            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values (12345,'Mexalen','50','ST','000123');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values (12346,'Aggafin','100','ML','000124');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values (12347,'Aspirin','20','ST','000125');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values (12348,'Neoangin','10','ST','000126');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values (12349,'Nasivin','15','ML','000127');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values (12300,'Prospan','20','ML','000128');");
        }

    }

    private boolean checkDbExists() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DBPath + DBName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

// database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }
}