package com.example.guest.umpireindicator.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Game;
import com.example.guest.umpireindicator.ui.GameDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.timeStamp) TextView mTimeStamp;
    @Bind(R.id.awayTeamScore) TextView mAwayText;
    @Bind(R.id.homeTeamScore) TextView mHomeText;
    @Bind(R.id.inningText) TextView mInningText;

    private Context mContext;
    private ArrayList<Game> mGames = new ArrayList<>();

    public GameViewHolder(View itemView, ArrayList<Game> games){
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mGames = games;
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent (mContext, GameDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("games", Parcels.wrap(mGames));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindGame(Game game){
        mTimeStamp.setText("Saved on : " + game.getTimeStamp());
        mAwayText.setText(game.getAway()+" " + game.getAwayTeamScore());
        mHomeText.setText(game.getHome()+" " + game.getHomeTeamScore());
        mInningText.setText(game.getInningFormat());
    }

}
