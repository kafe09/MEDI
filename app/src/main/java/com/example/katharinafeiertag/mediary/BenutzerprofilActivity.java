package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benutzerprofil);
        imageView = (ImageView) findViewById(R.id.iv_insertProfilimage);
        session = new SessionManager(this);
        helper = new DatabaseHelperContacts(this);

        SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);
        displayName = preferences.getString("displayName", "");  //ist der Username der gerade eingeloggt ist

        Log.d(TAG,"benutzer eingeloggt: " +displayName );

        nachname = (TextView) findViewById(R.id.tf_name);
        nachname.setText(helper.searchNachname(displayName));

        vorname = (TextView) findViewById(R.id.tf_vorname);
        vorname.setText(helper.searchVorname(displayName));

        email = (TextView) findViewById(R.id.tf_email);
        email.setText(helper.searchEmail(displayName));

        benutzername = (TextView) findViewById(R.id.tf_benutzername);
        benutzername.setText(displayName);


        /*if(!session.loggedIn()) {
            logout();
        }
        bt_logout = (Button)findViewById(R.id.bt_logout);
        bt_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
            }
        });*/
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
        //super.onActivityResult(requestCode, resultCode, data);
       // if (resultCode == RESULT_OK) {
          //  if (requestCode == CAMERA_REQUEST_CODE) {
                if (requestCode == IMAGE_GALLERY_REQUEST) {
                    //address of image on SD Card
                    Uri imageUri = data.getData();

                    //read the image data from  SD Card
                    InputStream inputStream;
                    try {
                        inputStream = getContentResolver().openInputStream(imageUri);
                        //get a bitmap from the stream
                        Bitmap image = BitmapFactory.decodeStream(inputStream);
                        BitmapDrawable drawable = new BitmapDrawable(image);
                        //imageView.setImageBitmap(image);
                        imageView.setImageDrawable(drawable);
                        Log.d(TAG,"Benutzerfoto hinzugefügt" +image);
                        Toast.makeText(this, "Benutzerfoto wurde festgelegt", Toast.LENGTH_LONG).show();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Öffnen des Bildes nicht möglich", Toast.LENGTH_LONG).show();
                    }
                }
            }
        //}
        //
   // }



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
