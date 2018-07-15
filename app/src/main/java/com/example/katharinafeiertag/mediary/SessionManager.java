package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by katharinafeiertag on 07.04.18.
 */

//diese Klasse wird benötigt um die UserID an die anderen Activities weiterzugeben

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "MEDI";

    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_userid = "userid";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Konstruktor
    public SessionManager(Context context){
        this.ctx = context;
        pref = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }


     //Create login session
    public void setLoggedin(boolean loggedin){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // commit changes
        editor.commit();
    }


    /**
     * Get stored session data
     * */
    public HashMap <String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_userid, pref.getString(KEY_userid, null));

        //user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }


}
