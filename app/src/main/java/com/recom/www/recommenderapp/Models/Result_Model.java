package com.recom.www.recommenderapp.Models;

public class Result_Model {
    private String name,price,location,distance,genre,thumb;
    private float rating;

    public Result_Model() {
    }

    public Result_Model(String name,String genre,String price,String location,String distance,String thumb,float rating){
        this.name=name;
        this.rating=rating;
        this.distance=distance;
        this.genre=genre;
        this.price=price;
        this.location=location;
        this.thumb=thumb;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
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