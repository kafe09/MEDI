package com.example.katharinafeiertag.mediary;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ActivityCompat;
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

/**
 * Created by katharinafeiertag on 10.07.18.
 */

public class FingerprintManager {
    private static FingerprintManager instance;
    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";
    private KeyGenerator keyGenerator;
    KeyguardManager keyguardManager;
    private android.hardware.fingerprint.FingerprintManager.CryptoObject cryptoObject;
    private android.hardware.fingerprint.FingerprintManager fingerprintManager;


    private FingerprintManager(Context context){
        keyguardManager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
    }


    public static FingerprintManager Instance(Context context){
        if(instance==null){
            instance = new FingerprintManager(context);
        }
        return instance;
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
        } catch (KeyPermanentlyInvalidatedException e) {
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

    private void generateKey() throws CertificateException, NoSuchAlgorithmException, IOException, FingerprintException {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
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

    public void setFingerprint (Context context) {
        android.hardware.fingerprint.FingerprintManager fingerprintManager = (android.hardware.fingerprint.FingerprintManager)context.getSystemService(Context.FINGERPRINT_SERVICE);

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(context, "Kein Fingerprint-Sensor gefunden.", Toast.LENGTH_LONG).show();
        } if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Erlauben Sie Fingerabdruck in Ihren Einstellungen.", Toast.LENGTH_LONG).show();
        } if (!fingerprintManager.hasEnrolledFingerprints()){
            Toast.makeText(context, "Definieren Sie einen Fingerabdruck in Ihren Einstellungen.", Toast.LENGTH_LONG).show();
        } if (!keyguardManager.isKeyguardSecure()) {
            Toast.makeText(context, "Das Entsperren mit Fingerprint wird in den Einstellungen nicht erlaubt.", Toast.LENGTH_LONG).show();
        } else
            try {
                generateKey();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FingerprintException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();

            }
        if (cipherInit()) {
            //FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            cryptoObject = new android.hardware.fingerprint.FingerprintManager.CryptoObject(cipher);

            FingerprintHandler helper = new FingerprintHandler(context);
            helper.startAuth(fingerprintManager, cryptoObject);
        }
    }



    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }


}
