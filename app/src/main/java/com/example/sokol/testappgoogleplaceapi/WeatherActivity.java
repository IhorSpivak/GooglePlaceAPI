package com.example.sokol.testappgoogleplaceapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Sokol on 18.03.2017.
 */

public class WeatherActivity extends Activity {
    private String city;
    private String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Intent intent = getIntent();

        city = intent.getStringExtra("City");
        countryCode = intent.getStringExtra("ContryCode");
    }
}
