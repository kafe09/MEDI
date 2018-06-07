package com.example.katharinafeiertag.mediary;

import android.app.KeyguardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";

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
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        /*FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (!fingerprintManager.isHardwareDetected())
            Toast.makeText(this, "Fingerprint kann leider nicht verwendet werden.", Toast.LENGTH_SHORT).show();
        else {
            if (!fingerprintManager.hasEnrolledFingerprints())
                Toast.makeText(this, "Definieren Sie einen Fingerabdruck in Ihren Einstellungen.", Toast.LENGTH_SHORT).show();
            else {
                if (!keyguardManager.isKeyguardSecure())
                    Toast.makeText(this, "Das Entsperren mit Fingerprint wird in den Einstellungen nicht erlaubt.", Toast.LENGTH_SHORT).show();
                else

                    try {
                        genKey();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (CertificateException e) {
                        e.printStackTrace();

                        if (cipherInit()) {
                            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                            FingerprintHandler helper = new FingerprintHandler(this);
                            helper.startAuth(fingerprintManager, cryptoObject);
                        }

                    }
            }
        }*/
        helper = new DatabaseHelperContacts(this);
        session = new SessionManager(this);

        tf_fingerprint = (TextView) findViewById(R.id.tf_fingerprinta);
        iv_finger = (ImageView) findViewById(R.id.iv_fingerprint);
        tf_info = (TextView) findViewById(R.id.tf_fingerinfo);

        username = (EditText) findViewById(R.id.tf_allergien);
        passwort = (EditText) findViewById(R.id.tf_passwort);
    }



        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                // generateKey();
                // if (cipherInit()) {
                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
            }
        }*/


    public void genKey() throws CertificateException, NoSuchAlgorithmException, IOException {
        try {

            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
                e.printStackTrace();
            }

            KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }

            try {
                keyStore.load(null);

                keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC) .setUserAuthenticationRequired(true)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                        .build());
                keyGenerator.generateKey();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }

        }



    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);

            SecretKey key = (SecretKey)keyStore.getKey(KEY_NAME,null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;

        }  catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (CertificateException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
            return false;
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }

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


