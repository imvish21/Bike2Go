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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bike2go.R;
import com.example.bike2go.adapters.HomeAdapter;
import com.example.bike2go.listeners.ItemListener;
import com.example.bike2go.model.Item;
import com.example.bike2go.model.User;
import com.example.bike2go.screens.DetailsActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment implements ItemListener {

     private RecyclerView topDealRV;
     private HomeAdapter adapter;
     private List<Item> itemList;
     private CircleImageView profileImage;
     private TextView username;
    private DatabaseReference ref;

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
         profileImage = view.findViewById(R.id.profile_image);
         username = view.findViewById(R.id.user_name);

        ref= FirebaseDatabase.getInstance().getReference().child("users");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);
                if(user != null){
                    username.setText(user.getName());
                    Glide.with(getContext())
                            .load(user.getImage())
                            .centerCrop()
                            .placeholder(R.drawable.ic_account)
                            .into(profileImage);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




          itemList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("images")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            itemList.add(new Item(
                                    Objects.requireNonNull(dataSnapshot.child("price").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("description").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("shortDescription").getValue()).toString(),
                                    Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString()
                            ));
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

       // itemList.add(new Item("Rs.400","Ninja H2R"));

         adapter = new HomeAdapter(getContext(),itemList,this);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
         linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         topDealRV.setLayoutManager(linearLayoutManager);
         topDealRV.setAdapter(adapter);

    }

    @Override
    public void OnItemPosition(int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);

        intent.putExtra("price",itemList.get(position).getPrice());
        intent.putExtra("description",itemList.get(position).getDescription());
        intent.putExtra("shortDescription",itemList.get(position).getShortDescription());
        intent.putExtra("image",itemList.get(position).getImage());


        startActivity(intent);
    }
}