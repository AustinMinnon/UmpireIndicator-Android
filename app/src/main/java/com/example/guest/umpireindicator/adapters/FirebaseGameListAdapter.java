package com.example.guest.umpireindicator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Game;
import com.example.guest.umpireindicator.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

public class FirebaseGameListAdapter extends FirebaseRecyclerAdapter<GameViewHolder, Game>{
    public FirebaseGameListAdapter(Query query, Class<Game> itemClass) {
        super(query, itemClass);
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_list_item, parent, false);
        return new GameViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position){
        holder.bindGame(getItem(position));
    }

    @Override
    protected void itemAdded(Game item, String key, int position){

    }

    @Override
    protected void itemChanged(Game oldItem, Game newItem, String key, int position){

    }

    @Override
    protected void itemRemoved(Game item, String key, int position){

    }

    @Override
    protected void itemMoved(Game item, String key, int oldPosition, int newPosition){

    }
}