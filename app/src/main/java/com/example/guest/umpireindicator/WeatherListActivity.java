package com.example.guest.umpireindicator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.guest.umpireindicator.WeatherService.fetchWeather;

public class WeatherListActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        fetchWeather(location);
    }

    private void fetchWeather(String location){

        final WeatherService weatherService = new WeatherService();

        WeatherService.fetchWeather(location, new Callback() {
        @Override
            public void onFailure(Call call, IOException e) {e.printStackTrace();}

        @Override
            public void onResponse(Call call, Response response) throws IOException{
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()){
                        Log.v("JSON DATA", jsonData);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
