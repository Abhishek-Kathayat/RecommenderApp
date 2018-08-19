package com.recom.www.recommenderapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.recom.www.recommenderapp.Models.Home_Model;
import com.recom.www.recommenderapp.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private final List<JsonObject> jsonlist;
    private List<Home_Model> itemlist;

    public HomeAdapter(List<JsonObject> jsonlist) {
        this.jsonlist=jsonlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, mile, genre,name,price;
        public RatingBar rating;
        public ImageView image;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            mile = (TextView) view.findViewById(R.id.mile);
            name=(TextView)view.findViewById(R.id.name);
            image=view.findViewById(R.id.image);
            price=view.findViewById(R.id.price);
            rating=view.findViewById(R.id.ratingbar);
        }
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        JsonObject item = jsonlist.get(position);
        JsonObject reviews = (JsonObject) item.get("reviews");
        JsonObject location =(JsonObject) item.get("location");
        holder.title.setText(String.valueOf(item.get("title")).replaceAll("\"", ""));
        holder.genre.setText(String.valueOf(item.get("category")).replaceAll("\"", ""));
        holder.mile.setText(String.valueOf(location.get("city")).replaceAll("\"", ""));
        holder.name.setText(String.valueOf(item.get("name")).replaceAll("\"", ""));
        holder.price.setText(String.valueOf(item.get("value")));
        holder.rating.setRating(Float.valueOf(String.valueOf(reviews.get("rating"))));
        /*
        Glide.with(holder.image.getContext())
                .load(String.valueOf(item.get("img_url")))
                .into(holder.image);
        */
    }

    @Override
    public int getItemCount() {
        return jsonlist.size();
    }
}