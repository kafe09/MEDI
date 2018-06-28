package com.example.katharinafeiertag.mediary;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//Hier sieht man die Benutzerdaten des Benutzers
public class BenutzerprofilActivity extends AppCompatActivity {
    public static final String TAG = DatabaseHelperContacts.class.getSimpleName();
    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;
    private Button bt_logout;
    SessionManager session;
    DatabaseHelperContacts helper;
    String displayName;
    TextView nachname, vorname, benutzername, email, allergien;

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







    //folgende drei Methoden: damit Benutzer ein Profilbild hinzufügen kann
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = getPath(data.getData());
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        // cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }

    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
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
