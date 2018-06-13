package com.example.katharinafeiertag.mediary;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.jar.JarEntry;

/**
 * Created by katharinafeiertag on 13.06.18.
 */

public class DataParser {

    private HashMap<String, String> getPlace(JSONObject googlePlaseJason) {
        HashMap<String, String> googlePlacesMap = new HashMap<>();

        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitute = "";
        String longitute = "";
        String reference = "";

        try {
            if (!googlePlaseJason.isNull("name")) {
                placeName = googlePlaseJason.getString("name");

            }
            if (!googlePlaseJason.isNull("viciniy")) {
                vicinity = googlePlaseJason.getString("vicinity");
            }

            latitute = googlePlaseJason.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitute = googlePlaseJason.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference = googlePlaseJason.getString("reference");

            googlePlacesMap.put("place_name", placeName);
            googlePlacesMap.put("vicinity", vicinity);
            googlePlacesMap.put("lat", latitute);
            googlePlacesMap.put("lng", longitute);
            googlePlacesMap.put("reference", reference);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlacesMap;
    }
}
