package com.example.guest.umpireindicator.ui;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.adapters.WeatherPagerAdapter;
import com.example.guest.umpireindicator.models.Weather;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDetailActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private WeatherPagerAdapter adapterViewPager;
    ArrayList<Weather> mWeathers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);
        mWeathers = Parcels.unwrap(getIntent().getParcelableExtra("weathers"));
        checkDay();
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new WeatherPagerAdapter(getSupportFragmentManager(), mWeathers);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }

    private void checkDay(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Log.d(TAG, "" + day);
//        switch (day) {
//            case Calendar.SUNDAY:
//                // Current day is Sunday
//
//            case Calendar.MONDAY:
//                // Current day is Monday
//
//            case Calendar.TUESDAY:
//                // etc.
//        }
    }
}
