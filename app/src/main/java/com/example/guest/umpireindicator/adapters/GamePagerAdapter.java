package com.example.guest.umpireindicator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.umpireindicator.models.Game;
import com.example.guest.umpireindicator.ui.GameDetailFragment;

import java.util.ArrayList;

public class GamePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Game> mGames;

    public GamePagerAdapter(FragmentManager fm, ArrayList<Game> games){
        super(fm);
        mGames = games;
    }
    @Override
    public Fragment getItem(int position){
        return GameDetailFragment.newInstance(mGames.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mGames.get(position).getTimeStamp();
    }
g
    @Override
    public int getCount(){
        return mGames.size();
    }
}
