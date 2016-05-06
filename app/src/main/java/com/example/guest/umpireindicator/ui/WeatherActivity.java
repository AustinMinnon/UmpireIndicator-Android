package com.example.guest.umpireindicator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = WeatherActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.weatherButton) Button mWeatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mWeatherButton.setOnClickListener(this);
        Intent weatherIntent = getIntent();

    }

    @Override
    public void onClick(View v) {
        if (v == mWeatherButton) {
            String location = mLocationEditText.getText().toString();
            if (!(location).equals("")){
                addToSharedPreferences(location);
            }
            Intent intent = new Intent(WeatherActivity.this, WeatherListActivity.class);
            startActivity(intent);
        }
    }
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.preferencesLocation, location).apply();
    }

}
