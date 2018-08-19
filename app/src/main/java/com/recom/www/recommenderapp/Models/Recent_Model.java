package com.recom.www.recommenderapp.Models;

public class Recent_Model {
    private String name;
    private float rating;

    public Recent_Model() {
    }

    public  Recent_Model(String name,float rating){
        this.name=name;
        this.rating=rating;
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