package com.example.katharinafeiertag.mediary;

import android.app.KeyguardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tf_info;
    private SessionManager session;
    DatabaseHelperContacts helper;
    private EditText username,passwort;
    public String usernameEingabe, passwortEingabe;

    private TextView tf_fingerprint;
    private ImageView iv_finger;

    public FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        helper = new DatabaseHelperContacts(this);
        session = new SessionManager(this);

        //tf_fingerprint = (TextView) findViewById(R.id.tf_fingerprinta);
        iv_finger = (ImageView) findViewById(R.id.iv_fingerprint);
        tf_info = (TextView) findViewById(R.id.tf_fingerinfo);

        username = (EditText) findViewById(R.id.tf_benutzername);
        passwort = (EditText) findViewById(R.id.tf_passwort);

     //   com.example.katharinafeiertag.mediary.FingerprintManager.Instance(this).setFingerprint(this);
    }


    public void registrieren(View v) {
        Intent Intent = new Intent(getBaseContext(), RegistrierungActivity.class);
        startActivity(Intent);
    }


    public void anmelden (View v) {
        usernameEingabe = username.getText().toString();
        passwortEingabe = passwort.getText().toString();

        String password = helper.searchPass(usernameEingabe);
        if(passwortEingabe.equals(password)) {
            session.setLoggedin(true);

            SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);
            String userDetailName = preferences.getString("Kein Benutzername vorhanden",usernameEingabe);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("displayName", userDetailName);
            editor.commit();

            Intent i = new Intent(LoginActivity.this, HauptmenuActivity.class);
            startActivity(i);
            finish();

        } else {
            Toast temp = Toast.makeText(LoginActivity.this, "Benutzername und Passwort stimmen nicht!", Toast.LENGTH_SHORT);
            temp.show();
        }
    }

    //wegen session erstellt
    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                break;
            case R.id.bt_registrieren:
                break;
            default:
        }
    }
}


