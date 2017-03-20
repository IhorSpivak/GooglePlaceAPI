package com.example.sokol.testappgoogleplaceapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng loacation;
    private double longitude;
    private double latitude;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();

        longitude = intent.getDoubleExtra(MainActivity.KEY_LONGITUDE, 0.0);
        latitude = intent.getDoubleExtra(MainActivity.KEY_LATITUDE, 0.0);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        loacation = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(loacation));
        map.moveCamera(CameraUpdateFactory.newLatLng(loacation));
    }
}
