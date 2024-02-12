package com.abhijeet.vitb;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment = new Mess();
        }
        else if (position == 1){
            fragment = new Mayuri();
        }
        else if (position == 2){
            fragment = new Underbely();
        }
        else if (position == 3){
            fragment = new NightCanteen();
        }
        else if (position == 4){
            fragment = new Settings();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
    @Override
    public CharSequence getPageTitle(int position){
        String title = null;
        if (position == 0){
            title="Mess";
        }
        else if (position == 1){
            title="Mayuri's";
        }
        else if (position == 2){
            title="Under Belly";
        }
        else if (position == 3){
            title="Night Canteen";
        }
        else if (position == 4){
            title="Settings";
        }
        return title;
    }
}

//app:tabMode="scrollable"
