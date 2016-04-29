package com.example.guest.umpireindicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = WeatherActivity.class.getSimpleName();

    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.weatherButton) Button mWeatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        mWeatherButton.setOnClickListener(this);


        Intent weatherIntent = getIntent();

    }

    @Override
    public void onClick(View v) {
        if (v == mWeatherButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(WeatherActivity.this, WeatherListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
