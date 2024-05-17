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
import com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategory;
import com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategoryAdapter;
import com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyItem;
import com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class Underbely extends Fragment implements UnderbelyCategoryAdapter.OnCategoryClickListener {
    private static final String UNDERBELY_CATEGORIES_KEY = "underbely_categories";
    private RecyclerView recyclerViewItems;
    private LinearLayout categoryContainer;
    private ImageView refreshButton, selectedItem;
    private List<UnderbelyCategory> underbelyCategoryList = new ArrayList<>();
    private List<UnderbelyItem> itemList = new ArrayList<>();
    private UnderbelyItemAdapter itemAdapter;
    private UnderbelyCategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_underbely, container, false);

        recyclerViewItems = view.findViewById(R.id.recyclerViewItems);
        categoryContainer = view.findViewById(R.id.categoryContainer);
        refreshButton = view.findViewById(R.id.refresh_button);
        selectedItem = view.findViewById(R.id.selected_item);

        // Setup item RecyclerView
        itemAdapter = new UnderbelyItemAdapter(itemList);
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
        String categoryJson = sharedPreferences.getString(UNDERBELY_CATEGORIES_KEY, null);

        if (categoryJson != null) {
            List<UnderbelyCategory> storedCategories = new Gson().fromJson(categoryJson, new TypeToken<List<UnderbelyCategory>>() {}.getType());
            underbelyCategoryList.addAll(storedCategories);
            populateCategoryContainer();
        } else {
            fetchDataFromFirebase();
        }
    }

    private void saveDataToSharedPreferences(List<UnderbelyCategory> categories) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String categoryJson = new Gson().toJson(categories);
        editor.putString(UNDERBELY_CATEGORIES_KEY, categoryJson);
        editor.apply();
    }

    private void fetchDataFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Under_Belly");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                underbelyCategoryList.clear();

                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    String categoryName = categorySnapshot.getKey();
                    List<UnderbelyItem> items = new ArrayList<>();

                    for (DataSnapshot itemSnapshot : categorySnapshot.child("Items").getChildren()) {
                        UnderbelyItem item = itemSnapshot.getValue(UnderbelyItem.class);
                        items.add(item);
                    }

                    UnderbelyCategory category = new UnderbelyCategory(categoryName, items);
                    underbelyCategoryList.add(category);
                }

                populateCategoryContainer();
                saveDataToSharedPreferences(underbelyCategoryList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Undebely", "Failed to read value.", error.toException());
            }
        });
    }

    private void populateCategoryContainer() {
        categoryContainer.removeAllViews();

        for (UnderbelyCategory category : underbelyCategoryList) {
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

    private void loadItems(UnderbelyCategory category) {
        itemList.clear();
        itemList.addAll(category.getItems());
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClick(UnderbelyCategory category) {
        for (UnderbelyCategory cat : underbelyCategoryList) {
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
