package com.example.guest.umpireindicator.ui;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.adapters.WeatherListAdapter;
import com.example.guest.umpireindicator.models.Weather;
import com.example.guest.umpireindicator.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentLocation;
    private WeatherListAdapter mAdapter;
    public ArrayList<Weather> mWeathers = new ArrayList<>();
    public static final String TAG = WeatherListActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface scoreboard_font = Typeface.createFromAsset(getAssets(), "fonts/scoreboardFont2.ttf");
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if (mRecentLocation != null) {
            fetchWeather(mRecentLocation);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                fetchWeather(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    private void fetchWeather(String location){
        final WeatherService weatherService = new WeatherService();
        final int itemPosition = getDay();

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
                        mRecyclerView.scrollToPosition(itemPosition);
                    }
                });
            }
        });
    }
    public int getDay(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day-1;
    }
}
