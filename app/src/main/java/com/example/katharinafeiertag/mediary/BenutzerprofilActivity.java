package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class BenutzerprofilActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benutzerprofil);
    }

    public void onAnmeldenClick (View v) {

        if (v.getId() == R.id.bt_anmelden)

        {
            EditText vorname = (EditText) findViewById(R.id.tf_vn);
            EditText name = (EditText) findViewById(R.id.tf_nm);
            EditText email = (EditText) findViewById(R.id.tf_mail);
            EditText passwort = (EditText) findViewById(R.id.tf_passwort);
            EditText passwort2 = (EditText) findViewById(R.id.tf_passwort2);


            String vornamestr = vorname.getText().toString();
            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String passwortstr = passwort.getText().toString();
            String passwort2str = passwort2.getText().toString();
            //Character geschlechtch = geschlecht.getText().charAt();


            if (!passwortstr.equals(passwort2str)) {

                Toast pass = Toast.makeText(BenutzerprofilActivity.this,"Passwörter stimmen nicht überein!",Toast.LENGTH_SHORT);
                pass.show();
            }

            else {
                //insert details in database

                Contact c = new Contact();
                c.setVorname(vornamestr);
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setPasswort(passwortstr);
                //c.setGeschlecht(geschlechtch);

                helper.insertContact(c);

            }

        }
        }



    //damit Benutzer ein Profilbild hinzufügen kann

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

    public void onClickClose(View view) {
        //Aufgabenstellung: hier entsteht dann der Toast
        Intent returnIntent = new Intent();
        returnIntent.putExtra(HauptmenuActivity.REQUEST_RESULT,42);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void methodezumVersuch() {
        String name;
        int alter;
        char punkt;
    }
}
