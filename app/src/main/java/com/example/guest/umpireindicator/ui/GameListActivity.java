package com.example.guest.umpireindicator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.umpireindicator.Constants;
import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.adapters.FirebaseGameListAdapter;
import com.example.guest.umpireindicator.models.Game;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListActivity extends AppCompatActivity {
    private Query mQuery;
    private Firebase mFirebaseGameRef;
    private FirebaseGameListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mFirebaseGameRef = new Firebase(Constants.FIREBASE_URL_GAMES);

        setUpFirebaseQuery();
        setUpRecyclerView();

    }

    private void setUpFirebaseQuery(){
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String game = mFirebaseGameRef.child(userUid).toString();
        mQuery = new Firebase(game);
    }
    private void setUpRecyclerView(){
        mAdapter = new FirebaseGameListAdapter(mQuery, Game.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
