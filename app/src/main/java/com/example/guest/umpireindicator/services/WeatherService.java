package com.example.guest.umpireindicator.services;

import android.util.Log;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.models.Weather;
import com.example.guest.umpireindicator.ui.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {    public static void fetchWeather(String location, Callback callback){
    String apiKey = Constants.apiKey;
    String baseUrl = Constants.baseUrl;

    OkHttpClient client = new OkHttpClient.Builder()
            .build();

    HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.baseUrl).newBuilder();
    urlBuilder.addQueryParameter(Constants.zipParam, location);
    urlBuilder.addQueryParameter("appid", Constants.apiKey);
    String url = urlBuilder.build().toString();
    Log.d(TAG, url);

    Request request = new Request.Builder()
            .url(url)
            .build();

    Call call = client.newCall(request);
    call.enqueue(callback);
}
    public static final String TAG = MainActivity.class.getSimpleName();
    public ArrayList<Weather> processResults(Response response){
        ArrayList<Weather> weathers = new ArrayList<>();

    try{
        String jsonData = response.body().string();
        if (response.isSuccessful()){
            JSONObject weatherJSON = new JSONObject(jsonData);
            JSONArray listJSON = weatherJSON.getJSONArray("list");
            String[] days = new String[]{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
            for (int i = 0; i<listJSON.length(); i++){
                JSONObject dayJSON = listJSON.getJSONObject(i);
                String city = weatherJSON.getJSONObject("city").getString("name");
                String country = weatherJSON.getJSONObject("city").getString("country");
                String day = days[i];
                String tempMax = dayJSON.getJSONObject("temp").getString("max");
                String tempMin = dayJSON.getJSONObject("temp").getString("min");
                String main = dayJSON.getJSONArray("weather").getJSONObject(0).getString("main");
                String icon = dayJSON.getJSONArray("weather").getJSONObject(0).getString("icon");
                String description = dayJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                Weather weather = new Weather(city, country, tempMax, tempMin, main, description, day, icon);
                Log.d(TAG, "" + icon);
                Log.d(TAG, "" + description);
                weathers.add(weather);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
        return weathers;
    }
}
