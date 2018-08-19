package com.recom.www.recommenderapp.Models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Nearby_Model {
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;
    @SerializedName("category")
    private String genre;
    @SerializedName("img_url")
    private String imgUrl;
    @SerializedName("rating")
    private float rating;
    @SerializedName("address")
    private String address;
    @SerializedName("location")
    private JsonObject location;
    @SerializedName("reviews")
    private JsonObject reviews;
    @SerializedName("results")
    private List<Nearby_Model> results;

    public List<Nearby_Model> getResults() {
        return results;
    }

    public void setResults(List<Nearby_Model> results) {
        this.results = results;
    }


    public Nearby_Model() {
    }

    public  Nearby_Model(String name,String value,String genre,float rating,String imgUrl,JsonObject location,JsonObject reviews){
        this.name=name;
        this.genre=genre;
        this.value=value;
        this.rating=rating;
        this.imgUrl=imgUrl;
        this.location=location;
        this.reviews=reviews;
    }

    public JsonObject getReviews() {
        return reviews;
    }

    public void setReviews(JsonObject reviews) {
        this.reviews = reviews;
    }

    public JsonObject getLocation() {
        return location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(JsonObject location) {
        this.location = location;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}