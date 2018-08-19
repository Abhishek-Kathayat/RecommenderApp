package com.recom.www.recommenderapp.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.recom.www.recommenderapp.API.ApiClient;
import com.recom.www.recommenderapp.API.ApiInterface;
import com.recom.www.recommenderapp.Adapters.NearbyAdapter;
import com.recom.www.recommenderapp.MainActivity;
import com.recom.www.recommenderapp.Models.Nearby_Model;
import com.recom.www.recommenderapp.R;
import com.recom.www.recommenderapp.SearchActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class NearbyFragment extends Fragment {
    private List<Nearby_Model> itemlist = new ArrayList<>();
    List<JsonObject> jsonlist=new ArrayList<>();
    private RecyclerView recyclerView;
    private NearbyAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ShimmerFrameLayout mShimmerViewContainer;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.nearby_view, container, false);
        mShimmerViewContainer = rootview.findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootview.findViewById(R.id.SwipeRefreshLayout);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view_nearby);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NearbyAdapter(jsonlist);
        recyclerView.setAdapter(mAdapter);

        EditText search=rootview.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        loadData();
        return rootview;
    }

    private void loadData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<Nearby_Model>> call = apiService.getNearbyItems();
        call.enqueue(new Callback<List<Nearby_Model>>() {
            @Override
            public void onResponse(Call<List<Nearby_Model>>call, Response<List<Nearby_Model>> response) {
                int statusCode = response.code();
                jsonlist.clear();
                List<Nearby_Model> items = response.body();
                Iterator<Nearby_Model> it=items.iterator();
                Gson gson = new Gson();
                Log.i("Response",response.body().toString());
                while (it.hasNext()) {
                    String x = gson.toJson(it.next());
                    JsonObject jsonObject = gson.fromJson(x, JsonObject.class);
                    jsonlist.add(jsonObject);
                    it.remove();
                }
                Log.i("OK", String.valueOf(jsonlist));
                mAdapter = new NearbyAdapter(jsonlist);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<Nearby_Model>> call, Throwable t) {

            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }
    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
    }


