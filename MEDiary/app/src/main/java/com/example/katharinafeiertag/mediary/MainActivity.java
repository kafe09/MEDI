package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //klick auf Benutzerprofil-Button
    public void onUserClick (View v){
        //Here we create a variable called myIntent which is an instance of the Intent Class,
        // which we use to move between Activities.
        Intent userIntent = new Intent(getBaseContext(), BenutzerprofilActivity.class);
        startActivity(userIntent);
    }

    //klick auf Settings-Button
    public void onSettingsClick (View v){
        Intent settingsIntent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(settingsIntent);
    }

}
