package com.example.guest.umpireindicator.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.adapters.GamePagerAdapter;
import com.example.guest.umpireindicator.models.Game;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailActivity extends AppCompatActivity{
@Bind(R.id.viewPager)
ViewPager mViewPager;
    private GamePagerAdapter adapterViewPager;
    ArrayList<Game> mGames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);
        mGames = Parcels.unwrap(getIntent().getParcelableExtra("games"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new GamePagerAdapter(getSupportFragmentManager(), mGames);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
