package com.example.shankaratalk.adopterclasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shankaratalk.fragment.Addblog;
import com.example.shankaratalk.fragment.Home;
import com.example.shankaratalk.fragment.Notification;
import com.example.shankaratalk.fragment.Profile;

public class Pager_adopter extends FragmentPagerAdapter {
    public Pager_adopter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Home();
            case 1:
                return new Addblog();
            case 2:
                return new Notification();
            case 3:
                return new Profile();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
