package com.example.shankaratalk.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shankaratalk.R;
import com.example.shankaratalk.mainactivity.EditProfile;
import com.example.shankaratalk.mainactivity.Login_page;
import com.example.shankaratalk.modelclass.Create_user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Fragment {
    private CircleImageView profileImage;
    private TextView name;
    private TextView postcount;
    private TextView followercount;
    private TextView followingcount;
    private TextView email;
    private TextView identity;
    private TextView gendar;
    private TextView bio;
    private AppCompatImageView showmenu;
    private StorageReference mStorageRef;



    Create_user create_user;




    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImage = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.name);
        postcount = view.findViewById(R.id.postcount);
        followercount = view.findViewById(R.id.followercount);
        followingcount = view.findViewById(R.id.followingcount);
        email = view.findViewById(R.id.email);
        identity = view.findViewById(R.id.identity);
        gendar = view.findViewById(R.id.gendar);
        bio = view.findViewById(R.id.bio);
        showmenu = view.findViewById(R.id.showmenu);

        showmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu  popupMenu = new PopupMenu(getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.poppopmenuiteam,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.editprofile:
                                startActivity(new Intent(getContext(), EditProfile.class));
                                return true;
                            case R.id.logout:
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getContext(), Login_page.class));
                                getActivity().finish();

                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseDatabase.getInstance().getReference("All_users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                create_user = snapshot.getValue(Create_user.class);
                name.setText(create_user.getName());
                email.setText(create_user.getEmail());
                identity.setText(create_user.getIdentity());
                gendar.setText(create_user.getGender());
                bio.setText(create_user.getBio());
                postcount.setText(create_user.getPostcount());
                followercount.setText(create_user.getFollowers());
                followingcount.setText(create_user.getFollowing());

                if (create_user.getPhotolink() == null)
                {

                    mStorageRef = FirebaseStorage.getInstance().getReference();
                    StorageReference riversRef = mStorageRef.child("user_profile").child(FirebaseAuth.getInstance().getUid());
                    riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if (uri == null)
                            {
                            }
                            else
                            {
                                Picasso.get().load(uri).into(profileImage);
                                create_user.setPhotolink(uri.toString());
                            }
                        }
                    });
                }
                else
                {
                    profileImage.setImageResource(R.drawable.person);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}