package com.example.katharinafeiertag.mediary;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



//Zeigt via Google Maps den aktuellen Standort sowie Apotheken im Umkreis an
// wir müssen mit type-proximity arbeiten

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;

    private static final String TAG = "MapsActivity";
    private Boolean mLocationPermissionsGranted = false;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private double myLatitude = 0;
    private double myLongitude = 0;

    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps, mInfo, mPlacePicker;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    //private PlaceInfo mPlace;
    private Marker mMarker;


    @Override
    public void onMapReady(GoogleMap map) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = map;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            if (!mLocationPermissionsGranted) {
                Toast temp = Toast.makeText(MapsActivity.this, "Zugriff auf GoogleMaps wurde verweigert", Toast.LENGTH_SHORT);
                temp.show();
            }
            mMap.setMyLocationEnabled(true);

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getLocationPermission();

        //neu hinzugefügt
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Log.d(TAG, "in der Create Methode von GoogleMaps");

        Toast temp = Toast.makeText(MapsActivity.this, "Google Maps wurde gestartet", Toast.LENGTH_SHORT);
        temp.show();
    }

    public static MapsActivity newInstance() {
        MapsActivity fragment = new MapsActivity();
        return fragment;
    }

    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
    }

    public void onClickGps(View v) {
        Log.d(TAG, "onClick: clicked gps icon");
        getDeviceLocation();
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();

                            myLatitude = currentLocation.getLatitude();
                            myLongitude = currentLocation.getLongitude();
                            Log.d(TAG, "onComplete: found location! " + myLatitude + " " + myLongitude);

                            Toast.makeText(MapsActivity.this, "aktuelle Position:" + currentLocation.getLatitude() + " " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                            LatLng position = new LatLng(myLatitude, myLongitude);

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 14));
                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapsActivity.this, "Aktuelle Position konnte nicht gefunden werden", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        LatLng currentPosition = new LatLng(myLatitude, myLongitude);
        Log.d(TAG, "Location wird angezeigt " + currentPosition);
        //CameraUpdateFactory.zoomIn();
    }


    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permission");
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,   //Allows an app to access precise location.
                Manifest.permission.ACCESS_COARSE_LOCATION}; //Allows an app to access approximate location.

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Determine whether you have been granted a particular permission.

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);

            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

    }

   /* public void onClickPh(View v) {
        Intent i = new Intent(getBaseContext(), GetNearbyDataPh.class);
        startActivity(i);*/

        //https://www.youtube.com/watch?v=_Oljjn1fIAc
    //}
}












