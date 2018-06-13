package com.example.katharinafeiertag.mediary;

import android.os.AsyncTask;
import android.webkit.DownloadListener;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;

/**
 * Created by katharinafeiertag on 13.06.18.
 */

public class GetNearbyDataPh extends AsyncTask<Object, String, String>{

    String googlePlacesData;
    GoogleMap map;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        map = (GoogleMap)objects[0];
        url = (String) objects[1];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePlacesData = downloadURL.readUrl(url);

        }catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
    }
}
