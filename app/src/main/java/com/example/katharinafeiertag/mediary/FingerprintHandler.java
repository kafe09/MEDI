package com.example.katharinafeiertag.mediary;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

        this.update("Es ist ein AuthentifizierungError aufgetreten." +errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context, "Authentifizierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
        //this.update("Authentifizierung fehlgeschlagen", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error" + helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationFailed();
        context.startActivity(new Intent(context, HauptmenuActivity.class));
        Toast.makeText(context, "Sie haben nun Zungang zu der App", Toast.LENGTH_SHORT).show();
    }

    private void update(String s, boolean b) {

        TextView tf_fingerinfo = (TextView) ((Activity)context).findViewById(R.id.tf_fingerinfo);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.iv_fingerprint);

        tf_fingerinfo.setText(s);

        //wennn Error auftritt Farbe ändern, ansonsten bleibt es bei der gleichen Farbe
        if(b == false) {

            tf_fingerinfo.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));

        } else {

            tf_fingerinfo.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            //zusätzlich ein Bild pushen (Hackerl für erledigt)
            imageView.setImageResource(R.mipmap.done);

        }
    }
}
