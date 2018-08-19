package com.recom.www.recommenderapp.Models;

public class Review_Model {
    private String name,thumb,review,like,dislike;
    private float rating;

    public Review_Model() {
    }

    public  Review_Model(String name,float rating,String thumb,String review,String like,String dislike){
        this.name=name;
        this.rating=rating;
        this.thumb=thumb;
        this.review=review;
        this.like=like;
        this.dislike=dislike;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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