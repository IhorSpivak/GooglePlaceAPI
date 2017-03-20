package com.example.sokol.testappgoogleplaceapi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String QUESTION_MARK = "?";
    private static final String Q = "q";
    private static final String EQUALS = "=";
    private static final String COMA = ",";
    private static final String AND = "&";
    private static final String APPID = "appid";
    private static final String API_KEY = "e54a785c8d34d62979f862af20da0778";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

            showOnMap = (Button) findViewById(R.id.showOnMap);
            showOnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
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
        longitude = Double.parseDouble(intent.getStringExtra(MainActivity.KEY_LONGITUDE));
        latitude = Double.parseDouble(intent.getStringExtra(MainActivity.KEY_LATITUDE));

        String finalUrl = BASE_URL + QUESTION_MARK + Q + EQUALS
                + city + COMA + countryCode + AND + APPID + EQUALS + API_KEY;

        new WeatherTask().execute(finalUrl);

        gridview = (GridView) findViewById(R.id.gridview);
    }

    private class WeatherTask extends AsyncTask<String, Void, String> {

        public WeatherTask() {
        }

        protected String doInBackground(String... addresses) {
            InputStream in = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(addresses[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                in = conn.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return sb.toString();
        }

            protected void onPostExecute(String result) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                WeatherInfo weatherInfo = gson.fromJson(result, WeatherInfo.class);

                gridview.setAdapter(new WeatherInfoAdapter(WeatherActivity.this, weatherInfo.getList()));
            }
        }
    }