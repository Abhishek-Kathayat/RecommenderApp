package com.recom.www.recommenderapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.recom.www.recommenderapp.Models.Recent_Model;
import com.recom.www.recommenderapp.R;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.MyViewHolder> {

    private List<Recent_Model> itemlist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public RatingBar rating;

        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.name);
            rating=view.findViewById(R.id.ratingbar);
        }
    }


    public RecentAdapter(List<Recent_Model> itemlist) {
        this.itemlist = itemlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Recent_Model item = itemlist.get(position);
        holder.name.setText(item.getName());
        holder.rating.setRating(item.getRating());

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}