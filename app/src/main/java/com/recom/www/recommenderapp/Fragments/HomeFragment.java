package com.recom.www.recommenderapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.recom.www.recommenderapp.API.ApiClient;
import com.recom.www.recommenderapp.API.ApiInterface;
import com.recom.www.recommenderapp.Adapters.CustomGridViewActivity;
import com.recom.www.recommenderapp.Adapters.HomeAdapter;
import com.recom.www.recommenderapp.Adapters.NearbyAdapter;
import com.recom.www.recommenderapp.Models.Events;
import com.recom.www.recommenderapp.Models.GlobalBus;
import com.recom.www.recommenderapp.Models.Home_Model;
import com.recom.www.recommenderapp.Models.Nearby_Model;
import com.recom.www.recommenderapp.R;
import com.recom.www.recommenderapp.SearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {
    GridView androidGridView;
    private List<Home_Model> itemlist = new ArrayList<>();
    private RecyclerView recyclerView;
    List<JsonObject> jsonlist=new ArrayList<>();
    private HomeAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ShimmerFrameLayout mShimmerViewContainer;
    String[] gridViewString = {
            "Food", "Shopping", "Hot&New", "Delivery", "Bars", "Coffee",
            "Gas Station", "Drug Store", "Deals", "More"
    } ;
    int[] gridViewImageId = {
            R.drawable.ic_cutlery, R.drawable.ic_shopping_cart, R.drawable.ic_hot, R.drawable.ic_delivery_truck, R.drawable.ic_cheers, R.drawable.ic_coffee,
            R.drawable.ic_gasoline_pump, R.drawable.ic_pill, R.drawable.ic_bill, R.drawable.ic_plus
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview=inflater.inflate(R.layout.home_view, container, false);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view_home);
        mShimmerViewContainer = rootview.findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootview.findViewById(R.id.SwipeRefreshLayout);
        mAdapter = new HomeAdapter(jsonlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getContext(), gridViewString, gridViewImageId);
        androidGridView=(GridView)rootview.findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(getContext(), "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });
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

        // Sending Message
        Events.FragmentActivityMessage fragmentActivityMessageEvent =
                new Events.FragmentActivityMessage("From Home Fragment");
        GlobalBus.getBus().postSticky(fragmentActivityMessageEvent);
        return rootview;
    }

    private void loadData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<List<Home_Model>> call = apiService.getItemsHome();
        call.enqueue(new Callback<List<Home_Model>>() {
            @Override
            public void onResponse(Call<List<Home_Model>>call, Response<List<Home_Model>> response) {
                int statusCode = response.code();
                jsonlist.clear();
                List<Home_Model> items = response.body();
                Iterator<Home_Model> it=items.iterator();
                Gson gson = new Gson();
                Log.i("Response",response.body().toString());
                while (it.hasNext()) {
                    String x = gson.toJson(it.next());
                    JsonObject jsonObject = gson.fromJson(x, JsonObject.class);
                    jsonlist.add(jsonObject);
                    it.remove();
                }
                Log.i("OK", String.valueOf(jsonlist));
                mAdapter = new HomeAdapter(jsonlist);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(@NonNull Call<List<Home_Model>> call, @NonNull Throwable t) {

            }

        });
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMessage(Events.ActivityFragmentMessage activityFragmentMessage) {

        Toast.makeText(getContext(),activityFragmentMessage.getMessage(),
                Toast.LENGTH_SHORT).show();

        Toast.makeText(getContext(),"Location In Fragment",
                Toast.LENGTH_LONG).show();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!GlobalBus.getBus().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }

    }
    @Override
    public void onDetach() {
        super.onDetach();
        GlobalBus.getBus().unregister(this);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
