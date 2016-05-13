package com.example.guest.umpireindicator;

public class Constants {
    public static final String apiKey = BuildConfig.apiKey;
    public static final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?units=imperial&cnt=7&";
    public static final String locationParam = "q";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String KEY_USER_EMAIL = "email";
    public static final String FIREBASE_LOCATION_GAMES = "games";
    public static final String FIREBASE_URL_GAMES = FIREBASE_URL + "/"+ FIREBASE_LOCATION_GAMES;

}
