package com.recom.www.recommenderapp.Models;

import android.databinding.BaseObservable;

public class User_Model extends BaseObservable {
    private String date, time, name,thumb;
    private float rating;

    public User_Model() {
    }

    public User_Model(String date, String time, String name,float rating) {
        this.date=date;
        this.name=name;
        this.rating=rating;
        this.time=time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}