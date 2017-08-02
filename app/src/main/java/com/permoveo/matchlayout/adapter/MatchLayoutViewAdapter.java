package com.permoveo.matchlayout.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.permoveo.matchlayout.R;
import com.permoveo.matchlayout.model.Match;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by byfieldj on 8/1/17.
 *
   Adapter class that will bind our Match results to our RecyclerView
 */

public class MatchLayoutViewAdapter extends RecyclerView.Adapter<MatchLayoutViewAdapter.ViewHolder> {

    private ArrayList<Match> mMatches = new ArrayList<>();

    public MatchLayoutViewAdapter(){


    }

    public void appendResults(ArrayList<Match> results){
        this.mMatches = results;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item_cell, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String photoUrl = mMatches.get(position).getPhotoUrl();


        // Load the profile photo
        if(photoUrl != null && !photoUrl.isEmpty()) {
            Picasso.with(holder.mPhoto.getContext()).load(photoUrl).fit().into(holder.mPhoto);
        }

        // Display the username
        String username = mMatches.get(position).getUsername();
        if(username != null && !username.isEmpty()){
            holder.mUsername.setText(username);
        }

        // Display the age, city, and state code
        int age = mMatches.get(position).getAge();
        String cityName = mMatches.get(position).getCityName();
        String stateCode = mMatches.get(position).getState();

        holder.mAgeDesc.setText(age + "•" + cityName + ", " + stateCode);

        // Display the match %
        int matchPercentage = mMatches.get(position).getMatchPercentage() / 100;
        holder.mMatchPercentage.setText(matchPercentage + "%");

        // Let's give this cell some personality :)
        setAnimation(holder.mParent);


    }

    private void setAnimation(View v){
        YoYo.with(Techniques.BounceIn).duration(1000).playOn(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_parent)
        CardView mParent;

        @Bind(R.id.iv_avatar)
        ImageView mPhoto;

        @Bind(R.id.tv_username)
        TextView mUsername;

        @Bind(R.id.tv_age_desc)
        TextView mAgeDesc;

        @Bind(R.id.tv_match_percentage)
        TextView mMatchPercentage;

        @Bind(R.id.tv_match_label)
        TextView mMatchLabel;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
