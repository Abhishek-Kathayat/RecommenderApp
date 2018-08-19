package com.recom.www.recommenderapp.API;


import com.recom.www.recommenderapp.Models.Home_Model;
import com.recom.www.recommenderapp.Models.Nearby_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {
    @GET("posts/list")
    Call<List<Nearby_Model>> getNearbyItems();
    @GET("posts/list")
    Call<List<Home_Model>> getItemsHome();


/*
    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
*/
}