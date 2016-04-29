package com.example.guest.umpireindicator.models;

public class Weather {
    private String mTempMax;
    private String mTempMin;
    private String mDate;
    private String mDescription;
    private int mWind;

    public Weather(String tempMax, String tempMin){
        this.mTempMax = tempMax;
        this.mTempMin = tempMin;
    }

    public String getMaxTemp() {
        return mTempMax;
    }

    public String getMinTemp() {
        return mTempMin;
    }

    public String getDate() {
        return mDate;
    }

}
