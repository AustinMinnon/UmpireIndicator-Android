package com.example.guest.umpireindicator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Game;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder>{
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;
    public GameListAdapter(Context context, ArrayList<Game> games){
       mContext = context;
        mGames = games;
    }

    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        GameViewHolder = viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position){
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount(){
        return mGames.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.homeTeamScore) TextView homeTeamScore;
        @Bind(R.id.awayTeamScore) TextView awayTeamScore;
        @Bind(R.id.timeStamp) TextView timeStamp;
        private Context mContext;

        public GameViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGame(Game game){
            homeTeamScore.setText(game.getHomeTeamScore());
            awayTeamScore.setText(game.getAwayTeamScore());
            timeStamp.setText(game.getTimeStamp());
        }

    }
}
