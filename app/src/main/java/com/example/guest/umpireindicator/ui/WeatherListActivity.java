package com.example.guest.umpireindicator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.adapters.WeatherListAdapter;
import com.example.guest.umpireindicator.models.Weather;
import com.example.guest.umpireindicator.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentLocation;
    public ArrayList<Weather> mWeathers = new ArrayList<>();
    public static final String TAG = WeatherListActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private WeatherListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentLocation = mSharedPreferences.getString(Constants.preferencesLocation, null);
        if (mRecentLocation != null) {
            fetchWeather(mRecentLocation);
        }
    }

    private void fetchWeather(String location){
        final WeatherService weatherService = new WeatherService();

        WeatherService.fetchWeather(location, new Callback() {
        @Override
            public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
            public void onResponse(Call call, Response response) {
            mWeathers = weatherService.processResults(response);

                WeatherListActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        mAdapter = new WeatherListAdapter(getApplicationContext(), mWeathers);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WeatherListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}
