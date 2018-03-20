package com.example.katharinafeiertag.mediary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistrierenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrieren);
    }

    //klick auf Registrieren-Button
    public void onRegisterClick (View v){
        Intent RegisterIntent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(RegisterIntent);
    }
}
