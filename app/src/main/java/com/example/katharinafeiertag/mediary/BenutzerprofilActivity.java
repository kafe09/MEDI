package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//Hier sieht man die Benutzerdaten des Benutzers
public class BenutzerprofilActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;
    private Button bt_logout;
    SessionManager session;
    DatabaseHelperContacts helper;
    TextView nachname, vorname, benutzername, email, allergien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benutzerprofil);

        session = new SessionManager(this);
        helper = new DatabaseHelperContacts(this);

        SharedPreferences preferences = getSharedPreferences("MEDI", MODE_PRIVATE);
        String displayName = preferences.getString("displayName", "");  //ist der Username der gerade eingeloggt ist

        nachname = (TextView) findViewById(R.id.tf_name);
        nachname.setText(helper.searchNachname(displayName));

        vorname = (TextView) findViewById(R.id.tf_vorname);
        vorname.setText(helper.searchVorname(displayName));

        email = (TextView) findViewById(R.id.tf_email);
        email.setText(helper.searchEmail(displayName));

        benutzername = (TextView) findViewById(R.id.tf_benutzername);
        benutzername.setText(displayName);

        benutzername = (TextView) findViewById(R.id.tf_allergien);
        benutzername.setText("keine Daten");

        if(!session.loggedIn()) {
            logout();
        }
        bt_logout = (Button)findViewById(R.id.bt_logout);
        bt_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }


    private void logout () {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(BenutzerprofilActivity.this, LoginActivity.class));
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

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }




}
