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
    @Bind(R.id.restaurantImageView) ImageView mImageLabel;
    @Bind(R.id.restaurantNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

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

        mNameLabel.setText(mWeather.getMaxTemp());
        mPhoneLabel.setText(mWeather.getMinTemp());
        mAddressLabel.setText(mWeather.getDate());
        return view;
    }
}