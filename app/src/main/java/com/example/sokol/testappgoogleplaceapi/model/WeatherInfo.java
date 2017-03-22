package com.example.sokol.testappgoogleplaceapi.model;

import java.util.List;

/**
 * Created by Sokol on 20.03.2017.
 */

public class WeatherInfo {
    private List<WeatherDetails> list;

    public class Main {
        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    public class Weather {
        private String icon;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public class WeatherDetails{
        private Main main;
        private List<Weather> weather;

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }
    }

    public List<WeatherDetails> getList() {
        return list;
    }

    public void setList(List<WeatherDetails> list) {
        this.list = list;
    }
}