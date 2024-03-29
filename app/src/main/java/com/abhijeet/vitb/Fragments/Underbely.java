package com.abhijeet.vitb.Fragments;

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
import android.widget.ImageView;

import com.abhijeet.vitb.MayuriRetrieval.MayuriCategory;
import com.abhijeet.vitb.MayuriRetrieval.MayuriCategoryAdapter;
import com.abhijeet.vitb.MayuriRetrieval.MayuriItem;
import com.abhijeet.vitb.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Underbely#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Underbely extends Fragment {

    ImageView refresh_button;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<MayuriCategory> underbelyCategoryList;
    private MayuriCategoryAdapter underbelyCategoryAdapter;
    private RecyclerView recyclerView;

    public Underbely() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Underbely.
     */
    // TODO: Rename and change types and number of parameters
    public static Underbely newInstance(String param1, String param2) {
        Underbely fragment = new Underbely();
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
        View rootView = inflater.inflate(R.layout.fragment_underbely, container, false);

        refresh_button = rootView.findViewById(R.id.refresh_button);
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation = new RotateAnimation(360, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                rotateAnimation.setDuration(500);
                refresh_button.startAnimation(rotateAnimation);
            }
        });

        RecyclerView recyclerViewCategories = rootView.findViewById(R.id.underbelyRecyclerView);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        underbelyCategoryList = new ArrayList<>();
        underbelyCategoryAdapter = new MayuriCategoryAdapter(underbelyCategoryList, getContext());

        recyclerViewCategories.setAdapter(underbelyCategoryAdapter);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Under_Belly");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                underbelyCategoryList.clear();

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()){
                    String categoryName = categorySnapshot.getKey();
                    List<MayuriItem> items = new ArrayList<>();

                    for (DataSnapshot itemSnapshot : categorySnapshot.child("Items").getChildren()){
                        MayuriItem mayuriItem = itemSnapshot.getValue(MayuriItem.class);
                        items.add(mayuriItem);
                    }

                    MayuriCategory mayuriCategory = new MayuriCategory(categoryName, items);
                    underbelyCategoryList.add(mayuriCategory);

//                    MayuriItem mayuriItem = snapshot.getValue(MayuriItem.class);
//                    Log.d("BookData", "Title: " + mayuriItem.getName() + ", Author: " + mayuriItem.getPrice());
//                    mayuriItemList.add(mayuriItem);
                }

                underbelyCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("MYLOG3", "Failed");
            }
        });

        return rootView;
    }
}




