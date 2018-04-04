package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RegistrierenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrieren);
    }

    //klick auf Registrieren-Button
    public void onRegisterClick (View v){
        Intent RegisterIntent = new Intent(getBaseContext(), Login2Activity.class);
        startActivity(RegisterIntent);
    }
}
