package com.example.katharinafeiertag.mediary;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Login2Activity extends AppCompatActivity {

    private TextView tf_fingerprint;
    private ImageView iv_finger;
    private TextView tf_info;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        tf_fingerprint = (TextView) findViewById(R.id.tf_fingerprinta);
        iv_finger = (ImageView) findViewById(R.id.iv_fingerprint);
        tf_info = (TextView) findViewById(R.id.tf_fingerinfo);

        //Hier wird überprüft ob wir berechtigt sind Fingerprint zu verwenden und ob alles funktionieren würde.
        //5 verschiedene Abfragen
        //1. Android version should be greater or equal to Marshmallow
        //2. Device has Fingerprint Scanner
        //3. Have permission to use fingerprint scanner in the app
        //4. Lock screen is secured with at least 1 type of lock
        //5. At least 1 fingerprint is registered

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {

                tf_info.setText("Fingerprint Scanner ist nicht zugänglich!");

            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

                tf_info.setText("Fingerprint zu verwenden ist nicht erlaubt");

            } else if (keyguardManager.isKeyguardSecure()) {

                tf_info.setText("Add Lock to your Phone in Settings");

            } else if (fingerprintManager.hasEnrolledFingerprints()) {

                tf_info.setText("Um dieses Feature nutzen zu können müssen Sie mindestens einen Fingerscan festlegen.");
            } else {

                tf_info.setText("Platzieren Sie Ihren Finger am Scanner um Eintritt in die App zu bekommen.");
                generateKey();
                if (cipherInit()) {
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                    fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void generateKey() {
        try {

            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance( KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC) .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {
            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;

        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }



    public void registrieren (View v){
        Intent Intent = new Intent(getBaseContext(), BenutzerprofilActivity.class);
        startActivity(Intent);
    }

    public void anmelden (View v) {
            EditText username = (EditText) findViewById(R.id.tf_benutzername);
            String usernameEingabe = username.getText().toString();
            EditText passwort = (EditText) findViewById(R.id.tf_passwort);
            String passwortEingabe = passwort.getText().toString();

            String password = helper.searchPass(usernameEingabe);
            if(passwortEingabe.equals(password)) {

                Intent i = new Intent(Login2Activity.this, HauptmenuActivity.class);
                startActivity(i);

            } else {
                Toast temp = Toast.makeText(Login2Activity.this, "Benutzername und Passwort stimmen nicht!", Toast.LENGTH_SHORT);
                temp.show();
            }
        }
    }

