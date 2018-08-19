package com.recom.www.recommenderapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recom.www.recommenderapp.Models.Recent_Model;
import com.recom.www.recommenderapp.Models.Review_Model;
import com.recom.www.recommenderapp.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    private List<Review_Model> itemlist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,like,dislike,review;
        public RatingBar rating;
        public ImageView thumb;

        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.name);
            rating=view.findViewById(R.id.user_rating);
            review=view.findViewById(R.id.review);
            like=view.findViewById(R.id.like_count);
            dislike=view.findViewById(R.id.dislike_count);
            thumb=view.findViewById(R.id.user_thumb);

        }
    }


    public ReviewAdapter(List<Review_Model> itemlist) {
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Review_Model item = itemlist.get(position);
        holder.name.setText(item.getName());
        holder.rating.setRating(item.getRating());
        holder.review.setText(item.getReview());
        holder.like.setText(item.getLike());
        holder.dislike.setText(item.getDislike());
        Glide.with(holder.thumb.getContext())
                .load(item.getThumb())
                .into(holder.thumb);


    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}