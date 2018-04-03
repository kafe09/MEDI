package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login2Activity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }

    public void registrieren (View v){
        Intent Intent = new Intent(getBaseContext(), BenutzerprofilActivity.class);
        startActivity(Intent);
    }

    public void anmelden (View v) {

        if (v.getId() == R.id.bt_login) {


            EditText a = (EditText) findViewById(R.id.tf_benutzername);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.tf_passwort);
            String pass = b.getText().toString();

            String passwort = helper.searchPass(str);

            if (pass.equals(passwort)) {

                Intent i = new Intent(Login2Activity.this, HauptmenuActivity.class);
                startActivity(i);

            } else {
                Toast temp = Toast.makeText(Login2Activity.this, "Benutzername und Passwort stimmen nicht!", Toast.LENGTH_SHORT);
                temp.show();
            }


        }
    }
}
