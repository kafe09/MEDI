package com.example.katharinafeiertag.mediary;

import android.app.KeyguardManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import android.util.Log;

import com.google.android.gms.common.data.DataBufferObserverSet;

//Hier sieht man die Benutzerdaten des Benutzers
public class BenutzerprofilActivity extends AppCompatActivity {
    public static final String TAG = DatabaseHelperContacts.class.getSimpleName();
    private static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int CAMERA_REQUEST_CODE = 228;
    private ImageView imageView;
    private Button bt_logout;
    SessionManager session;
    DatabaseHelperContacts helper;
    String displayName;
    TextView nachname, vorname, benutzername, email;
    Switch simpleSwitch;

    private CompoundButton.OnCheckedChangeListener fingerprintSwitchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton cb, boolean on) {
            SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);
            displayName = preferences.getString("displayName", "");  //ist der Username der gerade eingeloggt ist

            DatabaseHelperContacts db = new DatabaseHelperContacts(getBaseContext());
            Contact c = db.GetContactByUserName(displayName);
            c.setFingerPrintenabled(on ? "1" : "0");
            Log.d(TAG, "setting fingerprint enabled to " + c.getFingerPrintenabled());
            db.insertContact(c);

            if (on) {
                Log.d("set Fingerprint:", "in onCheckChanged Methode");
                Toast.makeText(getBaseContext(), "Bestätigen Sie durch berühren des Fingerprint-Sensors", Toast.LENGTH_LONG).show();
                FingerprintManager.Instance(getBaseContext()).setFingerprint(getBaseContext());
            } else {
            //do nothing
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benutzerprofil);
        imageView = (ImageView) findViewById(R.id.iv_insertProfilimage);
        simpleSwitch = (Switch) findViewById(R.id.switch_finger);
        session = new SessionManager(this);
        helper = new DatabaseHelperContacts(this);


        SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);

        displayName = preferences.getString("displayName", "");  //ist der Username der gerade eingeloggt ist
        Contact c = new DatabaseHelperContacts(this).GetContactByUserName(displayName);

        simpleSwitch.setChecked(c.getFingerPrintenabled());


        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("Off"); // displayed text of the Switch whenever it is in unchecked i.e. off state

        //Set a CheckedChange Listener for Switch Button
        simpleSwitch.setOnCheckedChangeListener(fingerprintSwitchListener);

        Log.d(TAG,"benutzer eingeloggt: " +displayName + ", " + c.getFingerPrintenabled());

        nachname = (TextView) findViewById(R.id.tf_name);
        nachname.setText(helper.searchNachname(displayName));

        vorname = (TextView) findViewById(R.id.tf_vorname);
        vorname.setText(helper.searchVorname(displayName));

        email = (TextView) findViewById(R.id.tf_email);
        email.setText(helper.searchEmail(displayName));

        benutzername = (TextView) findViewById(R.id.tf_benutzername);
        benutzername.setText(displayName);
    }

    public void selectImage(View v) {
        Log.d(TAG,"Benutzerfoto "+ "klick auf Foto hinzufügen");

        Intent photopickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);

        photopickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photopickerIntent, IMAGE_GALLERY_REQUEST);
    }


    //folgende zwei Methoden: damit Benutzer ein Profilbild hinzufügen kann
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG," in der onActivityResult Methode" );
                if (requestCode == IMAGE_GALLERY_REQUEST) {
                    Uri imageUri = data.getData();
                    //read the image data from  SD Card
                    InputStream inputStream;
                    try {
                        inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap image = BitmapFactory.decodeStream(inputStream);
                        BitmapDrawable drawable = new BitmapDrawable(image);
                        imageView.setImageDrawable(drawable);
                        Log.d(TAG,"Benutzerfoto hinzugefügt" +image);
                        Toast.makeText(this, "Benutzerfoto wurde festgelegt", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Öffnen des Bildes nicht möglich", Toast.LENGTH_LONG).show();
                    }
                }
            }



    public void benutzerloeschen(View v) {
        helper = new DatabaseHelperContacts(this);

        Log.d(TAG,"EMail und Name "+ helper.searchEmail(displayName) + helper.searchNachname(displayName));

        helper.db.delete("contacts", "email=? and name=?",new String[]{helper.searchEmail(displayName),helper.searchNachname(displayName)});

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void logout(View v) {
        // session.setLoggedin(false);
        // finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
