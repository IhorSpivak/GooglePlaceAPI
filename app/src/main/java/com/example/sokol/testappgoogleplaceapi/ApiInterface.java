package com.example.sokol.testappgoogleplaceapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sokol on 22.03.2017.
 */

public interface ApiInterface {
    @GET("forecast")
    Call<WeatherInfo> getWeatherInfo(@Query("q") String cityAndCountryCode, @Query("appid") String apiKey);
}
