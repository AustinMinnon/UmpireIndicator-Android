package com.example.guest.umpireindicator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class WeatherService {
    public static void fetchWeather(String location, Callback callback){
        String apiKey = Constants.apiKey;
        String baseUrl = Constants.baseUrl;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.baseUrl).newBuilder();
        urlBuilder.addQueryParameter(Constants.locationParam, location);
//        Plus US?
        urlBuilder.addQueryParameter(Constants.dayParam, "5");
        urlBuilder.addQueryParameter("appid", Constants.apiKey);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
