package com.abhijeet.vitb.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.abhijeet.vitb.MayuriRetrieval.MayuriCategory;
import com.abhijeet.vitb.MayuriRetrieval.MayuriCategoryAdapter;
import com.abhijeet.vitb.MayuriRetrieval.MayuriItem;
import com.abhijeet.vitb.MayuriRetrieval.MayuriItemAdapter;
import com.abhijeet.vitb.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class Mayuri extends Fragment implements MayuriCategoryAdapter.OnCategoryClickListener {
    private static final String MAYURI_CATEGORIES_KEY = "mayuri_categories";
    private RecyclerView recyclerViewItems;
    private LinearLayout categoryContainer;
    private ImageView refreshButton, selectedItem;
    private List<MayuriCategory> mayuriCategoryList = new ArrayList<>();
    private List<MayuriItem> itemList = new ArrayList<>();
    private MayuriItemAdapter itemAdapter;
    private MayuriCategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mayuri, container, false);

        recyclerViewItems = view.findViewById(R.id.recyclerViewItems);
        categoryContainer = view.findViewById(R.id.categoryContainer);
        refreshButton = view.findViewById(R.id.refresh_button);
        selectedItem = view.findViewById(R.id.selected_item);

        // Setup item RecyclerView
        itemAdapter = new MayuriItemAdapter(itemList);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewItems.setAdapter(itemAdapter);

        // Setup refresh button click listener
        refreshButton.setOnClickListener(v -> {
            // Handle refresh button click
            rotateRefreshButton();
            vibrate();
            showRefreshToast();
            // Refresh data from Firebase
            fetchDataFromFirebase();
        });

        // Setup selected item click listener
        selectedItem.setOnClickListener(v -> {
            openBottomSheet();
            haptic();
        });

        // Load data either from SharedPreferences or Firebase
        loadData();

        return view;
    }

    private void loadData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String categoryJson = sharedPreferences.getString(MAYURI_CATEGORIES_KEY, null);

        if (categoryJson != null) {
            List<MayuriCategory> storedCategories = new Gson().fromJson(categoryJson, new TypeToken<List<MayuriCategory>>() {}.getType());
            mayuriCategoryList.addAll(storedCategories);
            populateCategoryContainer();
        } else {
            fetchDataFromFirebase();
        }
    }

    private void saveDataToSharedPreferences(List<MayuriCategory> categories) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String categoryJson = new Gson().toJson(categories);
        editor.putString(MAYURI_CATEGORIES_KEY, categoryJson);
        editor.apply();
    }

    private void fetchDataFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Mayuri");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mayuriCategoryList.clear();

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    String categoryName = categorySnapshot.getKey();
                    List<MayuriItem> items = new ArrayList<>();

                    for (DataSnapshot itemSnapshot : categorySnapshot.child("Items").getChildren()) {
                        MayuriItem item = itemSnapshot.getValue(MayuriItem.class);
                        items.add(item);
                    }

                    MayuriCategory category = new MayuriCategory(categoryName, items);
                    mayuriCategoryList.add(category);
                }

                populateCategoryContainer();
                saveDataToSharedPreferences(mayuriCategoryList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Mayuri", "Failed to read value.", error.toException());
            }
        });
    }

    private void populateCategoryContainer() {
        categoryContainer.removeAllViews();

        for (MayuriCategory category : mayuriCategoryList) {
            View categoryView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, categoryContainer, false);
            ImageView categoryImage = categoryView.findViewById(R.id.categoryImage);
            TextView categoryTitle = categoryView.findViewById(R.id.categoryTitle);

            // Load image using your preferred image loading library (e.g., Glide or Picasso)
            // Glide.with(this).load(category.getImageUrl()).into(categoryImage);
            categoryTitle.setText(category.getTitle());

            categoryView.setOnClickListener(v -> onCategoryClick(category));
            categoryContainer.addView(categoryView);

            if (category.isSelected()) {
                categoryView.setBackgroundColor(getResources().getColor(R.color.light4));
            } else {
                categoryView.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }

    private void loadItems(MayuriCategory category) {
        itemList.clear();
        itemList.addAll(category.getItems());
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClick(MayuriCategory category) {
        for (MayuriCategory cat : mayuriCategoryList) {
            cat.setSelected(false);
        }
        category.setSelected(true);
        populateCategoryContainer();
        loadItems(category);
    }
    private void rotateRefreshButton() {
        RotateAnimation rotateAnimation = new RotateAnimation(360, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        rotateAnimation.setDuration(500);
        refreshButton.startAnimation(rotateAnimation);
    }

    private void showRefreshToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.fragment_toast_layout, null);
        TextView text = layout.findViewById(R.id.text_toast);
        text.setText("Refreshing...");
        Toast toast = new Toast(requireContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

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
