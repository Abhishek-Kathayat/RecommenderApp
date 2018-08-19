package com.recom.www.recommenderapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.recom.www.recommenderapp.Adapters.CustomGridViewActivity;
import com.recom.www.recommenderapp.Adapters.HomeAdapter;
import com.recom.www.recommenderapp.Adapters.UserAdapter;
import com.recom.www.recommenderapp.Models.Home_Model;
import com.recom.www.recommenderapp.Models.User_Model;
import com.recom.www.recommenderapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    GridView androidGridView;
    private List<User_Model> itemlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    String[] gridViewString = {
            "Bookmarks", "My Reviews", "My Check-ins", "Settings", "History", "Offers",
            "Friends","Messages"
    } ;
    int[] gridViewImageId = {
            R.drawable.ic_bookmark, R.drawable.ic_review, R.drawable.ic_street_map, R.drawable.ic_settings, R.drawable.ic_history, R.drawable.ic_discount,
            R.drawable.ic_facebook,R.drawable.ic_chat
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.user_view, container, false);
        RecyclerView recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view_user);

        mAdapter = new UserAdapter(itemlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareUserData();
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(getContext(), gridViewString, gridViewImageId);
        androidGridView=rootview.findViewById(R.id.options);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(getContext(), "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });
        return rootview;
    }

    private void prepareUserData() {
        User_Model item = new User_Model("23 May","19:08","Burger King",3.5f);
        itemlist.add(item);
        item = new User_Model("23 May","19:08","Burger King",3.5f);
        itemlist.add(item);
        item = new User_Model("23 May","19:08","Burger King",3.5f);
        itemlist.add(item);
        item = new User_Model("23 May","19:08","Burger King",3.5f);
        itemlist.add(item);
        item = new User_Model("23 May","19:08","Burger King",3.5f);
        itemlist.add(item);
        item = new User_Model("23 May","19:08","Burger King",3.5f);
        itemlist.add(item);
    }
}
