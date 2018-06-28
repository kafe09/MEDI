package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Hier kann sich der Benutzer neu registrieren und wird in einer Datenbank gespeichert
public class RegistrierungActivity extends AppCompatActivity {
    DatabaseHelperContacts helper = new DatabaseHelperContacts(this);
    EditText vorname, name, email, uname, passwort, passwort2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);
        helper = new DatabaseHelperContacts(this);

        vorname = (EditText) findViewById(R.id.tf_vn);
        name = (EditText) findViewById(R.id.tf_nm);
        email = (EditText) findViewById(R.id.tf_mail);
        uname = (EditText) findViewById(R.id.tf_uname);
        passwort = (EditText) findViewById(R.id.tf_passwort);
        passwort2 = (EditText) findViewById(R.id.tf_passwort2);
    }

    public void onRegistrierenClick (View v) {
        Intent regiintent = new Intent(getBaseContext(), LoginActivity.class );
        startActivity(regiintent);

            String vornamestr = vorname.getText().toString();
            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String passwortstr = passwort.getText().toString();
            String passwort2str = passwort2.getText().toString();

            if (vornamestr.isEmpty() || namestr.isEmpty() || emailstr.isEmpty() ||unamestr.isEmpty() || passwortstr.isEmpty()|| passwort2str.isEmpty()){
                displayToast ("Sie müssen alle Felder ausfüllen!");
                Intent hier = new Intent(getBaseContext(), RegistrierungActivity.class);
                startActivity(hier);
            }

            if (!passwortstr.equals(passwort2str)) {
                displayToast("Passwörter stimmen nicht überein!");

           // if(vornamestr.)

            } else {
                //insert details in database
                Contact c = new Contact();
                c.setVorname(vornamestr);
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPasswort(passwortstr);
                //c.setGeschlecht(geschlechtch);
                helper.insertContact(c);
            }
        }

    public void onClickClose(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(HauptmenuActivity.REQUEST_RESULT,42);
        setResult(RESULT_OK, returnIntent);
        finish();
    }


    public void displayToast (String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
