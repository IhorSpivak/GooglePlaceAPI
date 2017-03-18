package com.example.sokol.testappgoogleplaceapi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Sokol on 18.03.2017.
 */

public class FocusMap extends FragmentActivity {

        SupportMapFragment mapFragment;
        GoogleMap map;
        final String TAG = "myLogs";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.focus_map);

            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
//            map = mapFragment.getMap();
            if (map == null) {
                finish();
                return;
            }
            init();
        }

        private void init() {
        }


        public void onClickTest(View view) {
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

}
