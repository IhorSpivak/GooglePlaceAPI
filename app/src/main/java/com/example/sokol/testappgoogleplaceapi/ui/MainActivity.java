package com.example.sokol.testappgoogleplaceapi.ui;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sokol.testappgoogleplaceapi.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    public static final String KEY_CITY = "city";
    public static final String KEY_COUNTRY_CODE = "countryCode";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                LatLng latlng = place.getLatLng();

                Geocoder gcd = new Geocoder(this, Locale.ENGLISH);
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(latlng.latitude, latlng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String city = addresses.get(0).getLocality();
                String countryCode = addresses.get(0).getCountryCode();
                double longitude = place.getLatLng().longitude;
                double latitude = place.getLatLng().latitude;

                Intent intent = new Intent(this, WeatherActivity.class);
                intent.putExtra(KEY_CITY, city);
                intent.putExtra(KEY_COUNTRY_CODE, countryCode);
                intent.putExtra(KEY_LATITUDE, latitude);
                intent.putExtra(KEY_LONGITUDE, longitude);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
