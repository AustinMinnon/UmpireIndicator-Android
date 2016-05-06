package com.example.guest.umpireindicator.models;

import org.parceler.Parcel;

@Parcel
public class Weather {
    private String mCity;
    private String mCountry;
    private String mTempMax;
    private String mTempMin;
    private String mTempAvg;
    private String mMain;
    private String mDescription;
    private String mDay;

    public Weather(String city, String country, String tempMax, String tempMin, String tempAvg, String main, String description, String day){
        this.mCity = city;
        this.mCountry = country;
        this.mTempMax = tempMax;
        this.mTempMin = tempMin;
        this.mTempAvg = tempAvg;
        this.mMain = main;
        this.mDescription = description;
        this.mDay = day;

    }

    public String getCity(){
        return mCity;
    }

    public String getCountry(){
        return mCountry;
    }

    public String getMaxTemp() {
        return mTempMax;
    }

    public String getMinTemp() {
        return mTempMin;
    }

    public String getAvgTemp() {
        return mTempAvg;
    }

    public String getMain(){
        return mMain;
    }

    public String getDescription(){
        return mDescription;
    }

    public String getDay(){
        return mDay;
    }


    public Weather() {

    }

}
