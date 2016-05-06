package com.example.guest.umpireindicator.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Weather;
import com.example.guest.umpireindicator.ui.WeatherDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>{
    private ArrayList<Weather> mWeathers = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> weathers){
        mContext = context;
        mWeathers = weathers;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position){
        holder.bindWeather(mWeathers.get(position));
    }

    @Override
    public int getItemCount(){
        return mWeathers.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.restaurantImageView) ImageView mRestaurantImageView;
        @Bind(R.id.restaurantNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, WeatherDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("weathers", Parcels.wrap(mWeathers));
                    mContext.startActivity(intent);
                }
           });
        }

        public void bindWeather(Weather weather) {
            mNameTextView.setText(weather.getMinTemp());
            mCategoryTextView.setText(weather.getMaxTemp());
            mRatingTextView.setText(weather.getDay());
        }
    }

}
