package com.example.bike2go.fragments;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bike2go.R;
import com.example.bike2go.adapters.HomeAdapter;
import com.example.bike2go.listeners.ItemListener;
import com.example.bike2go.model.Item;
import com.example.bike2go.screens.DetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ItemListener {

     private RecyclerView topDealRV;
     private HomeAdapter adapter;
     private List<Item> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         topDealRV = view.findViewById(R.id.top_deal_RV);

          itemList = new ArrayList<>();
          itemList.add(new Item("Rs. 500","Ninja H2R"));
        itemList.add(new Item("Rs. 400","Yamaha MT 15"));
        itemList.add(new Item("Rs. 600"," Kawasaki Ninja 300"));
        itemList.add(new Item("Rs. 800","PULSAR RS 200"));

         adapter = new HomeAdapter(getContext(),itemList,this);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
         linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         topDealRV.setLayoutManager(linearLayoutManager);
         topDealRV.setAdapter(adapter);

    }

    @Override
    public void OnItemPosition(int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        startActivity(intent);
    }
}