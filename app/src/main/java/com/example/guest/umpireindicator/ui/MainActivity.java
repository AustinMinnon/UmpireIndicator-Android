package com.example.guest.umpireindicator.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.R;
import com.firebase.client.Firebase;

import org.w3c.dom.Text;

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
    @Bind(R.id.coachButton) Button mCoachButton;
    @Bind(R.id.weatherButton) Button mWeatherButton;
    @Bind(R.id.newBatter) Button mNewBatter;
    int ballCount=0;
    int strikeCount=0;
    int outCount=0;
    int homeCount=0;
    int awayCount=0;
    int inningCount=1;
    int inningTBCount=1;
    int upDown =0;

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


        mWeatherButton.setOnClickListener(this);
        mCoachButton.setOnClickListener(this);
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
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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

        @Override
        public void onClick(View v){
        switch(v.getId()){
            case R.id.coachButton:
                Intent intent = new Intent(MainActivity.this, CoachActivity.class);
                startActivity(intent);
            break;
            case R.id.weatherButton:
                Intent weatherIntent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(weatherIntent);
            break;
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