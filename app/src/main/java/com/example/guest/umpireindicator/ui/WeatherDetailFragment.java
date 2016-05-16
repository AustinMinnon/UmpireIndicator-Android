package com.example.guest.umpireindicator.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Weather;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDetailFragment extends Fragment {
    @Bind(R.id.weatherImageView) ImageView mWeatherImageView;
    @Bind(R.id.lowTextView) TextView mLowTextView;
    @Bind(R.id.highTextView) TextView mHighTextView;
    @Bind(R.id.dayTextView) TextView mDayTextView;
    @Bind(R.id.mainTextView) TextView mMainTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.locationTextView) TextView mLocationTextView;

    private Weather mWeather;

    public static WeatherDetailFragment newInstance(Weather weather) {
        WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("weather", Parcels.wrap(weather));
        weatherDetailFragment.setArguments(args);
        return weatherDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeather = Parcels.unwrap(getArguments().getParcelable("weather"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, view);

        mLocationTextView.setText(mWeather.getCity());
        mLowTextView.setText("Low: "+mWeather.getMinTemp()+"ºF");
        mHighTextView.setText("High: "+mWeather.getMaxTemp()+"ºF");
        mDayTextView.setText(mWeather.getDay());
        mDescriptionTextView.setText(mWeather.getDescription());
        mMainTextView.setText(mWeather.getMain());
        mWeatherImageView.setImageResource(mWeather.getIconPicture());
        return view;
    }
}