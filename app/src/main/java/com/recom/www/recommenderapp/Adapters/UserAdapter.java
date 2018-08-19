package com.recom.www.recommenderapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recom.www.recommenderapp.Models.Home_Model;
import com.recom.www.recommenderapp.Models.User_Model;
import com.recom.www.recommenderapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User_Model> itemlist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, time,name;
        RatingBar rating;

        public MyViewHolder(View view) {
            super(view);
            date=view.findViewById(R.id.date);
            time=view.findViewById(R.id.time);
            name=(TextView)view.findViewById(R.id.name);
            rating=view.findViewById(R.id.ratingbar);
        }
    }


    public UserAdapter(List<User_Model> itemlist) {
        this.itemlist = itemlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_activity_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User_Model item = itemlist.get(position);
        holder.name.setText(item.getName());
        holder.date.setText(item.getDate());
        holder.time.setText(item.getTime());
        holder.rating.setRating(item.getRating());
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}