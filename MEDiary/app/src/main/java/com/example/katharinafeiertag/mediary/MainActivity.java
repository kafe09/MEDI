package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //klick auf Benutzerprofil-Button
    public void onUserClick (View v){
            //Art: ActionView
        Intent userIntent = new Intent(Intent.ACTION_VIEW);
        userIntent.setData(Uri.parse("https://www.packtpub.com/"));
        startActivity(userIntent);
    }

    //klick auf Settings-Button
    public void onSettingsClick (View v){
            //Art: this
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    //klick auf Apotheken-Button
    public void onApothekenClick (View v){
            //Art: BaseContext
        Intent apothekenIntent = new Intent(getBaseContext(), ApothekenActivity.class);
        startActivity(apothekenIntent);
    }

    //klick auf MedEinnahme-Button
    public void onMedEinnahmeClick (View v){
        startActivity(new Intent(MainActivity.this, MedEinnahmeActivity.class));
    }

    //klick auf Hausapotheke-Button
    public void onHausapothekeClick (View v){
        Intent hpIntent = new Intent (this,HausapothekeActivity.class);
        final EditText tf_vorhanden = (EditText) findViewById(R.id.tf_editText);
        String userMessage = tf_vorhanden.getText().toString();
        hpIntent.putExtra("Hier sehen Sie Ihre Hausapotheke", userMessage);
        startActivity(hpIntent);
    }



}
