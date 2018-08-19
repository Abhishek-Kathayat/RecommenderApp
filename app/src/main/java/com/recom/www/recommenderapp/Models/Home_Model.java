package com.recom.www.recommenderapp.Models;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.recom.www.recommenderapp.BR;

import java.util.List;

public class Home_Model extends BaseObservable {


    @SerializedName("category")
    @Expose
    private
    String category;
    @SerializedName("range")
    @Expose
    private
    int range;
    @SerializedName("lat")
    @Expose
    private
    float lat;
    @SerializedName("lng")
    @Expose
    private
    float lng;

    @SerializedName("images")
    private JsonArray images;
    @SerializedName("location")
    private JsonObject location;
    @SerializedName("reviews")
    private JsonObject reviews;
    @SerializedName("title")
    private String title;
    @SerializedName("genre")
    private String genre;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("mile")
    private String mile;
    @SerializedName("rating")
    private int rating;
    @SerializedName("results")
    private List<Nearby_Model> results;

    public List<Nearby_Model> getResults() {
        return results;
    }

    public void setResults(List<Nearby_Model> results) {
        this.results = results;
    }

    public Home_Model() {
    }

    public Home_Model(String title, String genre, String name, String price, int rating, String imgUrl, String mile, String category, int range, float lat, float lng, JsonObject location,JsonObject reviews) {
        this.title = title;
        this.genre = genre;
        this.name = name;
        this.price=price;
        this.rating=rating;
        this.imgUrl=imgUrl;
        this.mile=mile;
        this.lat=lat;
        this.lng=lng;
        this.category=category;
        this.location=location;
        this.reviews=reviews;
    }

    @BindingAdapter({"imgUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
    public float getLat() {
        return lat;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public float getLng() {
        return lng;
    }

    public String getCategory() {
        return category;
    }
    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}