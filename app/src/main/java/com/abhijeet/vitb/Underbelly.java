package com.abhijeet.vitb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Underbelly extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodItem> foodItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underbelly);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data for food items
        foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItem("Pizza", "$12.99"));
        foodItemList.add(new FoodItem("Burger", "$8.99"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pizza", "$12.99"));
        foodItemList.add(new FoodItem("Burger", "$8.99"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));
        foodItemList.add(new FoodItem("Pasta", "$10.49"));


        // Add more food items as needed

        foodAdapter = new FoodAdapter(foodItemList);
        recyclerView.setAdapter(foodAdapter);
    }

    // Define the model class for food items
    static class FoodItem {
        private String name;
        private String price;

        public FoodItem(String name, String price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }
}
