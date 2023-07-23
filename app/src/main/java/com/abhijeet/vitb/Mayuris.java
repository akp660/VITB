package com.abhijeet.vitb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Mayuris extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Food_Adapter food_Adapter;
    private List<FoodItem> food_ItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayuris);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data for food items
        food_ItemList = new ArrayList<>();
        food_ItemList.add(new FoodItem("Veg Plait", "₹45.0"));
        food_ItemList.add(new FoodItem("Veg Sub Roll", "₹42.0"));
        food_ItemList.add(new FoodItem("Veg Turnover", "₹45.0"));
        food_ItemList.add(new FoodItem("Chilly Cheese Plait", "₹50.0"));
        food_ItemList.add(new FoodItem("Paneer Pizza Burn", "₹50.0"));
        food_ItemList.add(new FoodItem("Veg Crispy Roll", "₹50.0"));
        food_ItemList.add(new FoodItem("Chicken Turnover", "₹50.0"));
        food_ItemList.add(new FoodItem("Chicken Pizza Bun", "₹50.0"));
        food_ItemList.add(new FoodItem("Chicken Sub Roll", "₹50.0"));
        food_ItemList.add(new FoodItem("Chicken Plait", "₹53.0"));
        food_ItemList.add(new FoodItem("Egg Turnover", "₹40.0"));
        food_ItemList.add(new FoodItem("Veg Noodles", "₹150.0"));
        food_ItemList.add(new FoodItem("Veg Fried Rice", "₹130.0"));
        food_ItemList.add(new FoodItem("Chicken Noodles", "₹150.0"));
        food_ItemList.add(new FoodItem("Chicken Fried Rice", "₹150.0"));



        // Add more food items as needed

        food_Adapter = new Food_Adapter(food_ItemList);
        recyclerView.setAdapter(food_Adapter);
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
