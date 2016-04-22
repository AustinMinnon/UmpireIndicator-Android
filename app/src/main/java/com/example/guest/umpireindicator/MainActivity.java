package com.example.guest.umpireindicator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.ballText) TextView mBallText;
    @Bind(R.id.strikeText) TextView mStrikeText;
    @Bind(R.id.outText) TextView mOutText;
    @Bind(R.id.inningText) TextView mInningText;
    @Bind(R.id.plusBall) Button mPlusBall;
    @Bind(R.id.minusBall) Button mMinusBall;
    @Bind(R.id.plusStrike) Button mPlusStrike;
    @Bind(R.id.minusStrike) Button mMinusStrike;
    @Bind(R.id.plusOut) Button mPlusOut;
    @Bind(R.id.minusOut) Button mMinusOut;
    @Bind(R.id.coachButton) Button mCoachButton;
    @Bind(R.id.newBatter) Button mNewBatter;
    int ballCount=0;
    int strikeCount=0;
    int outCount=0;
    int inningCount=1;

    public void setCount(TextView balls, String type, int number ){
        balls.setText(type + number);
    }

    public void resetCount(TextView mBallText, TextView mOutText, TextView mStrikeText){
        ballCount=0;
        strikeCount=0;
        outCount=0;
        setCount(mBallText, "Ball ", ballCount);
        setCount(mStrikeText, "Strike ", strikeCount);
        setCount(mOutText, "Out ", outCount);
    }

    public void setCounts(){
        setCount(mBallText, "Ball ", ballCount);
        setCount(mStrikeText, "Strike ", strikeCount);
        setCount(mOutText, "Out ", outCount);
        setCount(mInningText, "Inning ", inningCount);
    }

    public void checkCounts(){
        if (strikeCount >=3 && outCount>=3) {
            ballCount = 0;
            strikeCount = 0;
            outCount=0;
            inningCount++;
            setCounts();
        } else if (outCount >=3){
            outCount=0;
            strikeCount=0;
            ballCount=0;
            inningCount+=1;
            setCounts();
        } else if (ballCount >=4){
            ballCount=0;
            strikeCount=0;
            setCount(mBallText, "Ball ", ballCount);
        }

        else if (strikeCount >=3){
            strikeCount-=3;
            setCount(mStrikeText, "Strike ", strikeCount);
            outCount++;
            if (outCount>=3) {
                ballCount = 0;
                strikeCount = 0;
                outCount=0;
                inningCount++;
                setCounts();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCoachButton.setOnClickListener(this);
        mNewBatter.setOnClickListener(this);
        mPlusBall.setOnClickListener(this);
        mMinusBall.setOnClickListener(this);
        mPlusStrike.setOnClickListener(this);
        mMinusStrike.setOnClickListener(this);
        mPlusOut.setOnClickListener(this);
        mMinusOut.setOnClickListener(this);
    }
        @Override
        public void onClick(View v){
        switch(v.getId()){
            case R.id.coachButton:
                Intent intent = new Intent(MainActivity.this, CoachActivity.class);
                startActivity(intent);
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
                strikeCount-=1;
                setCount(mStrikeText, "Strike ", strikeCount);
            break;
            case R.id.plusOut:
                outCount+=1;
                strikeCount=0;
                ballCount=0;
                checkCounts();
                setCounts();
            break;
            case R.id.minusOut:
                outCount-=1;
                setCount(mOutText, "Out ", outCount);
            break;
            case R.id.newBatter:
                resetCount(mBallText, mOutText, mStrikeText);
            break;
            default:

        }
    }
}