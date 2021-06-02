package com.example.shankaratalk.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shankaratalk.R;
import com.example.shankaratalk.adopterclasses.ForMyBlog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Addblog extends Fragment  {

    private TextView textView8;
    private EditText editTextTextMultiLine;
    private TextView myblog;
    private Button button3;
    private RecyclerView myblogesss;
    List<String> My_blogs = new ArrayList<>();









    public Addblog() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myVIew = inflater.inflate(R.layout.fragment_addblog, container, false);
        textView8 = myVIew.findViewById(R.id.textView8);
        editTextTextMultiLine = myVIew.findViewById(R.id.editTextTextMultiLine);
        myblog = myVIew.findViewById(R.id.myblog);
        button3 = myVIew.findViewById(R.id.button3);
        myblogesss = myVIew.findViewById(R.id.myblogesss);






        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String blog = editTextTextMultiLine.getText().toString().trim();
                Log.d("What",blog);
                if (blog.length()<5)
                {
                    Toast.makeText(getContext(), "Plese Enter A some Valid Blog", Toast.LENGTH_SHORT).show();
                }else
                {


                    FirebaseDatabase.getInstance().getReference("All_Blogs")
                            .child(FirebaseAuth.getInstance()
                                    .getUid()).push()
                            .setValue(blog).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Post Sucesfully", Toast.LENGTH_SHORT).show();
                            editTextTextMultiLine.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
        FirebaseDatabase.getInstance().getReference("All_Blogs").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    My_blogs.add(snapshot1.getValue(String.class));
//                    for(DataSnapshot snapshot2 : snapshot1.getChildren())
//                    {
//                        My_blogs.add(snapshot2.getValue(String.class));
//                    }
                }

                ForMyBlog forMyBlog = new ForMyBlog(getContext(),My_blogs);
                myblogesss.setLayoutManager(new LinearLayoutManager(getContext()));
                myblogesss.setHasFixedSize(true);
                myblogesss.setAdapter(forMyBlog);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        return myVIew;
    }
}