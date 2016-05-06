package com.example.guest.umpireindicator.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.umpireindicator.R;
import com.example.guest.umpireindicator.models.Weather;
import com.example.guest.umpireindicator.ui.WeatherDetailActivity;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
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
        @Bind(R.id.weatherImageView) ImageView mWeatherImageView;
        @Bind(R.id.lowTextView) TextView mLowTextView;
        @Bind(R.id.highTextView) TextView mHighTextView;
        @Bind(R.id.dayTextView) TextView mDayTextView;
        @Bind(R.id.mainTextView) TextView mMainTextView;
        @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
        @Bind(R.id.locationTextView) TextView mLocationTextView;
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
            mLocationTextView.setText(weather.getCity());
            mLowTextView.setText("Low: "+weather.getMinTemp()+"ºF");
            mHighTextView.setText("High: "+weather.getMaxTemp()+"ºF");
            mDayTextView.setText(weather.getDay());
            mDescriptionTextView.setText(weather.getDescription());
            mMainTextView.setText(weather.getMain());
        }
    }
}
