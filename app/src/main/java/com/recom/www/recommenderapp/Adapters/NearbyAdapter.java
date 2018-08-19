package com.recom.www.recommenderapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonObject;
import com.recom.www.recommenderapp.Models.Nearby_Model;
import com.recom.www.recommenderapp.Models.Recent_Model;
import com.recom.www.recommenderapp.R;

import org.json.JSONObject;

import java.util.List;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.MyViewHolder> {

    private final List<JsonObject> jsonlist;
    private List<Nearby_Model> itemlist;

    public NearbyAdapter(List<JsonObject> jsonlist) {
        this.jsonlist = jsonlist;
       // this.rowLayout = rowLayout;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,value,genre;
        public RatingBar rating;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.name);
            value=view.findViewById(R.id.value);
            genre=view.findViewById(R.id.genre);
            rating=view.findViewById(R.id.ratingbar);
            img=view.findViewById(R.id.pager);
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearby_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JsonObject item = jsonlist.get(position);
        holder.name.setText((item.get("name")).toString().replaceAll("\"", ""));
        JsonObject reviews = (JsonObject) item.get("reviews");
        JsonObject location =(JsonObject) item.get("location");
        JsonObject images = (JsonObject) item.get("images");
        holder.rating.setRating(Float.valueOf(String.valueOf(reviews.get("rating"))));
        holder.genre.setText((item.get("category")).toString().replaceAll("\"", ""));
        holder.value.setText((item.get("value")).toString().replaceAll("\"", ""));
        Glide.with(holder.img.getContext())
                .load(item.get("img_url"))
                .apply(new RequestOptions()

                        .centerCrop()
                )
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return jsonlist.size();
    }
}