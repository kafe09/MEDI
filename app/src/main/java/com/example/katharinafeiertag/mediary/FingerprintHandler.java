package com.example.katharinafeiertag.mediary;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//Dient zur Fingerprint-Authentifizierung des Benutzers für einen schnelleren Login
@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;

    //Konstruktor
    public FingerprintHandler(Context context) {
        this.context = context;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cacellationSignal = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject, cacellationSignal , 0, this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        if(errorCode != 5)
            Toast.makeText(context, "Es ist ein Fehler bei der Authentifizierung aufgetreten: " +errString + " (" + errorCode + ")", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context, "Authentifizierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
        //this.update("Authentifizierung fehlgeschlagen", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);

        SharedPreferences preferences = this.context.getSharedPreferences("MEDI", context.MODE_PRIVATE);
        String username = preferences.getString("displayName","");
        if(!username.equals("") ) {
            context.startActivity(new Intent(context, HauptmenuActivity.class));
            Toast.makeText(context, "Servus, " +preferences.getString("displayName","User"), Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Authentifikation fehlgeschlagen. Bitte registrieren Sie den Fingerprint neu.", Toast.LENGTH_SHORT).show();

    }

}
