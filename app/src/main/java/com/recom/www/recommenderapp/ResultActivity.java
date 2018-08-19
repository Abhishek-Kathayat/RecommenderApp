package com.recom.www.recommenderapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.recom.www.recommenderapp.Adapters.ResultAdapter;
import com.recom.www.recommenderapp.Models.Recent_Model;
import com.recom.www.recommenderapp.Models.Result_Model;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private List<Result_Model> itemlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResultAdapter mAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view_result);
        mAdapter = new ResultAdapter(itemlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareResult();



    }

    private void prepareResult() {
        Result_Model item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
        item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
        item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
        item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
        item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
        item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
        item=new Result_Model("Burger King","Food","999","Pacific Mall","12 Km","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Burger_King_Logo.svg/2000px-Burger_King_Logo.svg.png",3.5f);
        itemlist.add(item);
    }
}
