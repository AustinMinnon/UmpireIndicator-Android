package com.example.guest.umpireindicator.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Game;
import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mFirebaseRef;

    @Bind(R.id.ballText) TextView mBallText;
    @Bind(R.id.strikeText) TextView mStrikeText;
    @Bind(R.id.outText) TextView mOutText;
    @Bind(R.id.inningText) TextView mInningText;
    @Bind(R.id.homeText) TextView mHomeText;
    @Bind(R.id.awayText) TextView mAwayText;
    @Bind(R.id.plusBall) Button mPlusBall;
    @Bind(R.id.minusBall) Button mMinusBall;
    @Bind(R.id.plusStrike) Button mPlusStrike;
    @Bind(R.id.minusStrike) Button mMinusStrike;
    @Bind(R.id.plusOut) Button mPlusOut;
    @Bind(R.id.minusOut) Button mMinusOut;
    @Bind(R.id.plusInning) Button mPlusInning;
    @Bind(R.id.minusInning) Button mMinusInning;
    @Bind(R.id.plusAway) Button mPlusAway;
    @Bind(R.id.minusAway) Button mMinusAway;
    @Bind(R.id.plusHome) Button mPlusHome;
    @Bind(R.id.minusHome) Button mMinusHome;
//    @Bind(R.id.weatherButton) Button mWeatherButton;
    @Bind(R.id.newBatter) Button mNewBatter;
    @Bind(R.id.saveGameButton) Button mSaveGameButton;
//    @Bind(R.id.gameListButton) Button mGameListButton;
    private SharedPreferences mSharedPreferences;
    public String timeStamp;
    int ballCount=0;
    int strikeCount=0;
    int outCount=0;
    int homeCount=0;
    int awayCount=0;
    int inningCount=1;
    int inningTBCount=1;
    int upDown =0;
    String home ="Home";
    String away = "Away";

    public void setCount(TextView balls, String type, int number ){
        balls.setText(type + number);
    }

    public void resetCount(TextView mBallText, TextView mOutText, TextView mStrikeText) {
        ballCount = 0;
        strikeCount = 0;
        outCount = 0;
        setCount(mBallText, "Ball ", ballCount);
        setCount(mStrikeText, "Strike ", strikeCount);
        setCount(mOutText, "Out ", outCount);
    }

    public int checkInning(int inningTBCount){
        if (inningTBCount %2 ==1){
            upDown=1;
        }else if (inningTBCount %2==0){
            upDown=2;
        }else if (inningTBCount %3==0){
            upDown=3;
        }
        return upDown;

    }

    public void setCounts(){
        setCount(mBallText, "Ball ", ballCount);
        setCount(mStrikeText, "Strike ", strikeCount);
        setCount(mOutText, "Out ", outCount);
    }

    public void checkCounts(){
        if (strikeCount >=3 && outCount>=3) {
            ballCount = 0;
            strikeCount = 0;
            outCount=0;
            inningTBCount++;
            checkInning(inningTBCount);

            setCounts();
        } else if (outCount >=3){
            outCount=0;
            strikeCount=0;
            ballCount=0;
            inningTBCount++;
            checkInning(inningTBCount);
                if (upDown==1){
                    inningCount++;
                    setCount(mInningText, "Inning ↑", inningCount);

                }else if (upDown==2){
                    setCount(mInningText, "Inning ↓", inningCount);
                }
            setCounts();
        } else if (ballCount >=4){
            ballCount=0;
            strikeCount=0;
            setCount(mBallText, "Ball ", ballCount);
        } else if (strikeCount >=3){
            strikeCount=0;
            ballCount=0;
            outCount++;
            setCounts();

            if (outCount>=3) {
                ballCount = 0;
                strikeCount = 0;
                outCount=0;
                inningTBCount++;
                checkInning(inningTBCount);
                    if (upDown==1){
                        inningCount++;
                        setCount(mInningText, "Inning ↑", inningCount);

                    }else if (upDown==2){
                        setCount(mInningText, "Inning ↓", inningCount);
                }
                setCounts();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


//        mCoachButton.setOnClickListener(this);
//        mGameListButton.setOnClickListener(this);
        mSaveGameButton.setOnClickListener(this);
//        mWeatherButton.setOnClickListener(this);
        mNewBatter.setOnClickListener(this);
        mPlusBall.setOnClickListener(this);
        mMinusBall.setOnClickListener(this);
        mPlusStrike.setOnClickListener(this);
        mMinusStrike.setOnClickListener(this);
        mPlusOut.setOnClickListener(this);
        mMinusOut.setOnClickListener(this);
        mPlusInning.setOnClickListener(this);
        mMinusInning.setOnClickListener(this);
        mPlusHome.setOnClickListener(this);
        mMinusHome.setOnClickListener(this);
        mPlusAway.setOnClickListener(this);
        mMinusAway.setOnClickListener(this);
        mHomeText.setOnClickListener(this);
        mAwayText.setOnClickListener(this);
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        //add this to weather activity below
//        inflater.inflate(R.menu.menu_search, menu);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.weatherAction:
                Intent weatherIntent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(weatherIntent);
                return true;
            case R.id.action_logout:
                logout();
                return true;
            case R.id.gameAction:
                Intent listIntent = new Intent(MainActivity.this, GameListActivity.class);
                startActivity(listIntent);
        }
        return super.onOptionsItemSelected(item);
    }
//    public void onComposeAction(MenuItem mi) {
//        // handle click here
//    }

    protected void logout(){
        mFirebaseRef.unauth();
        takeUserToLoginScreenOnUnAuth();
    }

    private void takeUserToLoginScreenOnUnAuth() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public static String getCurrentTimeStamp(){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String currentTimeStamp = dateFormat.format(new Date());
            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        @Override
        public void onClick(View v){
        switch(v.getId()){
//            case R.id.coachButton:
//                Intent intent = new Intent(MainActivity.this, CoachActivity.class);
//                startActivity(intent);
            case R.id.homeText:
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
                View mView = layoutInflaterAndroid.inflate(R.layout.home_dialog, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
                alertDialogBuilderUserInput.setView(mView);
                final EditText userInput = (EditText) mView.findViewById(R.id.homeInput);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                home = userInput.getText().toString();
                                Toast.makeText(MainActivity.this, "Updated home team for future saves", Toast.LENGTH_SHORT).show();

                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
                break;
            case R.id.awayText:
                LayoutInflater layoutInflaterAndroid2 = LayoutInflater.from(this);
                View nView = layoutInflaterAndroid2.inflate(R.layout.away_dialog, null);
                AlertDialog.Builder alertDialogBuilderUserInput2 = new AlertDialog.Builder(this);
                alertDialogBuilderUserInput2.setView(nView);
                final EditText userInput2 = (EditText) nView.findViewById(R.id.awayInput);
                alertDialogBuilderUserInput2
                        .setCancelable(false)
                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                away = userInput2.getText().toString();
                                Toast.makeText(MainActivity.this, "Updated away team for future saves", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid2 = alertDialogBuilderUserInput2.create();
                alertDialogAndroid2.show();
            case R.id.saveGameButton:
                timeStamp=getCurrentTimeStamp();
                Game mGame = new Game(home, away, inningTBCount, homeCount, awayCount, timeStamp);
                String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
                Firebase userGameFirebaseRef = new Firebase(Constants.FIREBASE_URL_GAMES).child(userUid);
                Firebase pushRef = userGameFirebaseRef.push();
                String gamePushId = pushRef.getKey();
//                deny duplicates, by checking gamepushid, if matches, update
                mGame.setPushId(gamePushId);
                userGameFirebaseRef.push().setValue(mGame);
                Toast.makeText(this, "Saved Game", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.gameListButton:
//                Intent listIntent = new Intent(MainActivity.this, GameListActivity.class);
//                startActivity(listIntent);
//            case R.id.weatherButton:
//                Intent weatherIntent = new Intent(MainActivity.this, WeatherActivity.class);
//                startActivity(weatherIntent);
//            break;
            case R.id.plusBall:
                if (ballCount <=3){
                    ballCount++;
                    setCount(mBallText, "Ball ", ballCount);
                    checkCounts();
                    setCounts();
                }else {
                }
            break;
            case R.id.minusBall:
                if (ballCount ==0){
                }else {
                    ballCount-=1;
                    setCount(mBallText, "Ball ", ballCount);
                }
            break;
            case R.id.plusStrike:
                if (strikeCount <=2){
                    strikeCount++;
                    setCount(mStrikeText, "Strike ", strikeCount);
                    checkCounts();
                    setCounts();
                }else {
                }
            break;
            case R.id.minusStrike:
                if (strikeCount ==0){
                }else if (strikeCount >0){
                    strikeCount-=1;
                    setCount(mStrikeText, "Strike ", strikeCount);
                }
            break;
            case R.id.plusOut:
                outCount+=1;
                strikeCount=0;
                ballCount=0;
                checkCounts();
                setCounts();
            break;
            case R.id.minusOut:
                if (outCount ==0){
                }else if (outCount >0){
                    outCount-=1;
                    setCount(mOutText, "Out ", outCount);
                }
            break;
            case R.id.newBatter:
                resetCount(mBallText, mOutText, mStrikeText);
            break;
            case R.id.plusInning:
                inningTBCount++;
                checkInning(inningTBCount);
                if (upDown==1){
                    inningCount++;
                    setCount(mInningText, "Inning ↑", inningCount);

                }else if (upDown==2){
                    setCount(mInningText, "Inning ↓", inningCount);
                }
                break;
            case R.id.minusInning:
                if (inningCount >=1){
                    inningTBCount-=1;
                    setCounts();
                    checkInning(inningTBCount);
                    if (upDown==2){
                        inningTBCount++;
                        inningCount-=1;
                        setCount(mInningText, "Inning ↓", inningCount);
                    }else if (upDown==1){
                        inningTBCount++;
                        setCount(mInningText, "Inning ↑", inningCount);
                    }else if (upDown==3){
                        inningTBCount-=1;
                        setCount(mInningText, "Inning ↓", inningCount);
                    }
                }
                inningTBCount-=1;
                break;
            case R.id.plusHome:
                    homeCount++;
                    setCount(mHomeText, "Home ", homeCount);
                    setCounts();
                break;
            case R.id.minusHome:
                if (homeCount ==0){
                }else {
                    homeCount-=1;
                    setCount(mHomeText, "Home ", homeCount);
                }
                break;
            case R.id.plusAway:
                awayCount++;
                setCount(mAwayText, "Away ", awayCount);
                break;
            case R.id.minusAway:
                if (awayCount ==0){
                }else {
                    awayCount-=1;
                    setCount(mAwayText, "Away ", awayCount);
                }
                break;
            default:
                break;

        }
    }
}