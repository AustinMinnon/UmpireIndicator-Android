package com.example.guest.umpireindicator.models;

import org.parceler.Parcel;

@Parcel
public class Game {
    int homeTeamScore;
    int awayTeamScore;
    private String pushId;

    public Game() {}

    public Game(int awayTeamScore, int homeTeamScore){
        this.awayTeamScore = awayTeamScore;
        this.homeTeamScore = homeTeamScore;
        this.pushId = pushId;
    }
    
    public int getHomeTeamScore(){
        return  homeTeamScore;
    }
    public int getAwayTeamScore(){
        return awayTeamScore;
    }
    public String getPushId(){
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
