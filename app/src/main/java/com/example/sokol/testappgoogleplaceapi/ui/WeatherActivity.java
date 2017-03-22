package com.example.sokol.testappgoogleplaceapi.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.sokol.testappgoogleplaceapi.network.ApiClient;
import com.example.sokol.testappgoogleplaceapi.network.ApiInterface;
import com.example.sokol.testappgoogleplaceapi.R;
import com.example.sokol.testappgoogleplaceapi.ui.adapter.WeatherInfoAdapter;
import com.example.sokol.testappgoogleplaceapi.model.WeatherInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sokol on 18.03.2017.
 */

public class WeatherActivity extends Activity {
    private String city;
    private String countryCode;
    private double longitude;
    private double latitude;
    private GridView gridview;
    Button showOnMap;

    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    private static final String API_KEY = "e54a785c8d34d62979f862af20da0778";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        gridview = (GridView) findViewById(R.id.gridview);

        showOnMap = (Button) findViewById(R.id.showOnMap);
        showOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.showOnMap:
                        Intent intent = new Intent(WeatherActivity.this, MapActivity.class);
                        intent.putExtra(KEY_LATITUDE, latitude);
                        intent.putExtra(KEY_LONGITUDE, longitude);
                        startActivity(intent);
                }

            }
        });

        Intent intent = getIntent();

        city = intent.getStringExtra(MainActivity.KEY_CITY);
        countryCode = intent.getStringExtra(MainActivity.KEY_COUNTRY_CODE);
        longitude = intent.getDoubleExtra(MainActivity.KEY_LONGITUDE, 0.0);
        latitude = intent.getDoubleExtra(MainActivity.KEY_LATITUDE, 0.0);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        String cityAndCountryCode = city + "," + countryCode;

        Call<WeatherInfo> call = apiService.getWeatherInfo(cityAndCountryCode, API_KEY);
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                List<WeatherInfo.WeatherDetails> weatherDetailsList = response.body().getList();

                gridview.setAdapter(new WeatherInfoAdapter(WeatherActivity.this, weatherDetailsList));
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {

            }
        });
    }
}