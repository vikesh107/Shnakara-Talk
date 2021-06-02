package com.example.shankaratalk.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.shankaratalk.R;
import com.example.shankaratalk.adopterclasses.HomeAllBlog;
import com.example.shankaratalk.mainactivity.Chat_Home;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {
    private AppBarLayout appBarLayout;
    private ImageView goforchat;
    private RecyclerView recycalview;
    private RecyclerRefreshLayout recyclerRefreshLayout;
    HomeAllBlog homeAllBlog;
    private String key;

    List<String> uid = new ArrayList<>();
    List<String> blocs =new ArrayList<>();


    public Home() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appBarLayout = view.findViewById(R.id.appBarLayout);
        goforchat = view.findViewById(R.id.goforchat);
        recycalview = view.findViewById(R.id.recycalview);
        recyclerRefreshLayout = view.findViewById(R.id.refreslayout);

        goforchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Chat_Home.class));
            }
        });

        FirebaseDatabase.getInstance().getReference("All_Blogs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                     key = ds.getKey();
                   for( DataSnapshot sap : ds.getChildren())
                   {
                       blocs.add(sap.getValue(String.class));
                       uid.add(key);
                   }

                }

                homeAllBlog = new HomeAllBlog(getContext(), blocs,uid);
                recycalview.setHasFixedSize(true);
                recycalview.setLayoutManager(new LinearLayoutManager(getContext()));
                recycalview.setAdapter(homeAllBlog);

                homeAllBlog.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        recyclerRefreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().recreate();
                recyclerRefreshLayout.setRefreshing(false);

            }

        });





    }
}