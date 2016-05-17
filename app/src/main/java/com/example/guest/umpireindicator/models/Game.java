package com.example.guest.umpireindicator.models;

import org.parceler.Parcel;

@Parcel
public class Game {
    String home = "Home";
    String away = "Away";
    int inning;
    String inningFormat;
    int homeTeamScore;
    int awayTeamScore;
     String timeStamp;
     String pushId;


    public Game() {}

    public Game(String home, String away, int inning, int awayTeamScore, int homeTeamScore, String timeStamp){
        this.away = away;
        this.home = home;
        this.inning = inning;
        this.awayTeamScore = awayTeamScore;
        this.homeTeamScore = homeTeamScore;
        this.timeStamp = timeStamp;
        this.pushId = pushId;
    }
    public String getHome(){return home;}
    public String getAway(){return away;}
    public int getInning(){return inning;}
    public int getHomeTeamScore(){
        return  homeTeamScore;
    }
    public int getAwayTeamScore(){
        return awayTeamScore;
    }
    public String getTimeStamp(){
        return timeStamp;
    }
    public String getPushId(){
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
    public String getInningFormat(){
        if (inning %2 !=0){
            return inning /2+1 + "↑";
        }else{
            return inning /2 + "↓";
        }
    }

}
