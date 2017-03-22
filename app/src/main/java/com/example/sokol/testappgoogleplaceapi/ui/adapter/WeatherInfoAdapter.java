package com.example.sokol.testappgoogleplaceapi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sokol.testappgoogleplaceapi.R;
import com.example.sokol.testappgoogleplaceapi.model.WeatherInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sokol on 20.03.2017.
 */

public class WeatherInfoAdapter extends BaseAdapter {

    private Context mContext;
    private List<WeatherInfo.WeatherDetails> weatherList;


    public WeatherInfoAdapter(Context c, List<WeatherInfo.WeatherDetails> weatherList) {
        mContext = c;
        this.weatherList = weatherList;

    }

    public int getCount() {
        return weatherList.size();
    }

    public Object getItem(int position) {
        return weatherList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.cell_grid, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.image);
        TextView textView = (TextView) grid.findViewById(R.id.temp);
        String iconName = weatherList.get(position).getWeather().get(0).getIcon();
        int temp = (int)weatherList.get(position).getMain().getTemp() - 273;
        textView.setText(String.valueOf(temp) + " \u00b0");

        String iconUrl = "http://openweathermap.org/img/w/" + iconName + ".png";

        Picasso.with(mContext)
                .load(iconUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .into(imageView);

        return grid;
    }
}