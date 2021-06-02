package com.example.shankaratalk.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.shankaratalk.R;
import com.example.shankaratalk.adopterclasses.HomeAllBlog;
import com.example.shankaratalk.adopterclasses.Pager_adopter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomnavigationbar;
    private ViewPager viewpager;
    public String uid;
    public  String blogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomnavigationbar = findViewById(R.id.bottomnavigationbar);
        viewpager = findViewById(R.id.viewpager);

        Pager_adopter pager_adopter = new Pager_adopter(getSupportFragmentManager(),Pager_adopter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpager.setAdapter(pager_adopter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomnavigationbar.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        bottomnavigationbar.setSelectedItemId(R.id.addblog);
                        break;
                    case 2:
                        bottomnavigationbar.setSelectedItemId(R.id.notification);
                        break;
                    case 3:
                        bottomnavigationbar.setSelectedItemId(R.id.profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomnavigationbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        viewpager.setCurrentItem(0);
                        return true;
                    case R.id.addblog:
                        viewpager.setCurrentItem(1);
                        return true;
                    case R.id.notification:
                        viewpager.setCurrentItem(2);
                        return true;
                    case R.id.profile:
                        viewpager.setCurrentItem(3);
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

}