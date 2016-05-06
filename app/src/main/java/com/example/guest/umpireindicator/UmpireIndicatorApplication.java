package com.example.guest.umpireindicator;

import android.app.Application;

import com.firebase.client.Firebase;

public class UmpireIndicatorApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
