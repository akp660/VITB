package com.abhijeet.vitb.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.abhijeet.vitb.R;
import com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategory;
import com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategoryAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Underbely#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Underbely extends Fragment {

    ImageView refresh_button, selected_item;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<UnderbelyCategory> underbelyCategoryList;
    private UnderbelyCategoryAdapter underbelyCategoryAdapter;
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
//        refresh_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RotateAnimation rotateAnimation = new RotateAnimation(360, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
//                rotateAnimation.setDuration(500);
//                refresh_button.startAnimation(rotateAnimation);
//                vibrate();
//
//                // Show toast
//                // Create custom toast
//                LayoutInflater inflater = getLayoutInflater();
//                View layout = inflater.inflate(R.layout.fragment_toast_layout, null); // Remove the toast_layout_root parameter
//                TextView text = layout.findViewById(R.id.text_toast);
//                text.setText("Refreshing...");
//
//                Toast toast = new Toast(requireContext());
//                toast.setDuration(Toast.LENGTH_SHORT);
//                toast.setView(layout);
//                toast.show();
//            }
//        });


        // on click listner on refresh button
//        selected_item = rootView.findViewById(R.id.selected_item);
//        selected_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openBottomSheet();
//                haptic();
//            }
//        });

//        RecyclerView recyclerViewCategories = rootView.findViewById(R.id.underbelyRecyclerView);
//        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        underbelyCategoryList = new ArrayList<>();
//        underbelyCategoryAdapter = new UnderbelyCategoryAdapter(underbelyCategoryList, getContext());
//
//        recyclerViewCategories.setAdapter(underbelyCategoryAdapter);
//
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Under_Belly");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                underbelyCategoryList.clear();
//
//                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()){
//                    String categoryName = categorySnapshot.getKey();
//                    List<MayuriItem> items = new ArrayList<>();
//
//                    for (DataSnapshot itemSnapshot : categorySnapshot.child("Items").getChildren()){
//                        MayuriItem mayuriItem = itemSnapshot.getValue(MayuriItem.class);
//                        items.add(mayuriItem);
//                    }
//
//                    MayuriCategory mayuriCategory = new MayuriCategory(categoryName, items);
//                    underbelyCategoryList.add(underbelyCategory);
//
////                    MayuriItem mayuriItem = snapshot.getValue(MayuriItem.class);
////                    Log.d("BookData", "Title: " + mayuriItem.getName() + ", Author: " + mayuriItem.getPrice());
////                    mayuriItemList.add(mayuriItem);
//                }
//
//                underbelyCategoryAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("MYLOG3", "Failed");
//            }
//        });

        return rootView;
    }




    // Define the vibrate method outside of the onClick method
    private void vibrate() {
        Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(100);
            }
        }
    }

    private void haptic() {
        Vibrator vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(50);
            }
        }
    }

    private void openBottomSheet() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


}




