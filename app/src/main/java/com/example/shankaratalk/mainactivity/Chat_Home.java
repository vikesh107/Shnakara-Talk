package com.example.shankaratalk.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shankaratalk.R;
import com.example.shankaratalk.adopterclasses.chatrecycaler;
import com.example.shankaratalk.modelclass.Create_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chat_Home extends AppCompatActivity {
    private RecyclerView chatrecalerview;
    List<Create_user> Data_for_users = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat__home);


        chatrecalerview = findViewById(R.id.chatrecalerview);


        FirebaseDatabase.getInstance().getReference("All_users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             for (DataSnapshot ds : snapshot.getChildren())
             {
                 Create_user modelClassForemail = ds.getValue(Create_user.class);
                 Data_for_users.add(modelClassForemail);
             }

                chatrecycaler adopter = new chatrecycaler(Data_for_users,getApplicationContext());
                chatrecalerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                chatrecalerview.setHasFixedSize(true);
                chatrecalerview.setAdapter(adopter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}