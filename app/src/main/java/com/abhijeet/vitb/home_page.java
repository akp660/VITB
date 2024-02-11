package com.abhijeet.vitb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class home_page extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
//    MyViewPageAdapter myViewPageAdapter;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);

//        myViewPageAdapter = new MyViewPageAdapter(this);
//        viewPager2.setAdapter(myViewPageAdapter);
//        String mess = getResources().getString(R.string.Mess);
        adapter = new MainAdapter(getSupportFragmentManager());
        adapter.AddFragment(new Mess(), "Mess");
        adapter.AddFragment(new Mayuri(),"Mayuri's");
        adapter.AddFragment(new Underbely(), "Under Belly");
        adapter.AddFragment(new NightCanteen(), "Night Canteen");
        adapter.AddFragment(new Settings(), "Settings");
//
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager2.setCurrentItem( tab.getPosition());
////                Toast.makeText(home_page.this, "position : "+position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
    }
//
private class MainAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> stringArrayList = new ArrayList<>();

    public void AddFragment(Fragment fragment, String s){
        fragmentArrayList.add(fragment);
        stringArrayList.add(s);
    }
    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }
}
}

