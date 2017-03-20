package com.example.sokol.testappgoogleplaceapi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        Resources resources = mContext.getResources();

        int resourceId = resources.getIdentifier("a" + iconName, "drawable",
                mContext.getPackageName());
        Drawable drawable = resources.getDrawable(resourceId);

        imageView.setImageDrawable(drawable);
        textView.setText(String.valueOf(temp));

        return grid;
    }
}