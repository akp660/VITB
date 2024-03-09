package com.abhijeet.vitb;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mayuri#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mayuri extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<MayuriCategory> mayuriCategoryList;
    private MayuriCategoryAdapter mayuriCategoryAdapter;
//    private MayuriItemAdapter mayuriItemAdapter;
    private RecyclerView recyclerView;

    public Mayuri() {
        // Required empty public constructor
    }




    // TODO: Rename and change types and number of parameters
    public static Mayuri newInstance(String param1, String param2) {
        Mayuri fragment = new Mayuri();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mayuri, container, false);

        RecyclerView recyclerViewCategories = rootView.findViewById(R.id.mayuriRecyclerView);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        mayuriCategoryList = new ArrayList<>();
        mayuriCategoryAdapter = new MayuriCategoryAdapter(mayuriCategoryList, getContext());

        recyclerViewCategories.setAdapter(mayuriCategoryAdapter);

//        recyclerView = rootView.findViewById(R.id.mayuriRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        mayuriItemList = new ArrayList<>();
//        mayuriItemAdapter = new MayuriItemAdapter(mayuriItemList, getContext());
//        recyclerView.setAdapter(mayuriItemAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Mayuri");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mayuriCategoryList.clear();

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()){
                    String categoryName = categorySnapshot.getKey();
                    List<MayuriItem> items = new ArrayList<>();

                    for (DataSnapshot itemSnapshot : categorySnapshot.child("Items").getChildren()){
                        MayuriItem mayuriItem = itemSnapshot.getValue(MayuriItem.class);
                        items.add(mayuriItem);
                    }

                    MayuriCategory mayuriCategory = new MayuriCategory(categoryName, items);
                    mayuriCategoryList.add(mayuriCategory);

//                    MayuriItem mayuriItem = snapshot.getValue(MayuriItem.class);
//                    Log.d("BookData", "Title: " + mayuriItem.getName() + ", Author: " + mayuriItem.getPrice());
//                    mayuriItemList.add(mayuriItem);
                }

                mayuriCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("MYLOG3", "Failed");
            }
        });

        return rootView;


    }


}