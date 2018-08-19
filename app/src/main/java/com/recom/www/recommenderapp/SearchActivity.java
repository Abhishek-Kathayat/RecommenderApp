package com.recom.www.recommenderapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.recom.www.recommenderapp.Adapters.CustomGridViewActivity;
import com.recom.www.recommenderapp.Adapters.RecentAdapter;
import com.recom.www.recommenderapp.Adapters.UserAdapter;
import com.recom.www.recommenderapp.Models.Events;
import com.recom.www.recommenderapp.Models.GlobalBus;
import com.recom.www.recommenderapp.Models.Recent_Model;
import com.recom.www.recommenderapp.Models.User_Model;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity {
    private List<Recent_Model> itemlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecentAdapter mAdapter;
    private EditText location;
    GridView androidGridView;
    String[] gridViewString = {
            "Food", "Shopping", "Hot&New", "Delivery", "Bars", "Coffee",
            "Gas Station", "Drug Store", "Deals", "More"
    } ;
    int[] gridViewImageId = {
            R.drawable.ic_cutlery, R.drawable.ic_shopping_cart, R.drawable.ic_hot, R.drawable.ic_delivery_truck, R.drawable.ic_cheers, R.drawable.ic_coffee,
            R.drawable.ic_gasoline_pump, R.drawable.ic_pill, R.drawable.ic_bill, R.drawable.ic_plus
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_recent);
        mAdapter = new RecentAdapter(itemlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareRecentData();
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getApplicationContext(), gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(getApplicationContext(), "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });
        location =findViewById(R.id.location);
        TextView cancel=findViewById(R.id.cancel);
        TextView search=findViewById(R.id.search_action);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareRecentData() {
        Recent_Model item=new Recent_Model("Dominos",4f);
        itemlist.add(item);
        item=new Recent_Model("Burger King",3f);
        itemlist.add(item);
        item=new Recent_Model("Pizza Hut",2.5f);
        itemlist.add(item);
        item=new Recent_Model("Dominos",4f);
        itemlist.add(item);
        item=new Recent_Model("Burger King",3f);
        itemlist.add(item);
        item=new Recent_Model("Pizza Hut",2.5f);
        itemlist.add(item);


    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMessage(Events.ActivityActivityMessage ActivityMessage) {
        location.setText(ActivityMessage.getMessage());

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Register this fragment to listen to event.
        if (!GlobalBus.getBus().isRegistered(this)) {
            GlobalBus.getBus().register(this);
        }
    }
}
