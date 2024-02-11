package com.abhijeet.vitb;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPageAdapter extends FragmentStateAdapter {
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new Mess();
            case 1 :
                return new Mayuri();
            case 2 :
                return new Underbely();
            case 3 :
                return new NightCanteen();
            case 4 :
                return new Settings();
            default :
                return new Mess();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
