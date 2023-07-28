package com.abhijeet.vitb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Mayuris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayuris);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy data for food items
        List<FoodItem> food_ItemList = new ArrayList<>();
        food_ItemList.add(new FoodItem("Regular Tea", "₹15.0"));
        food_ItemList.add(new FoodItem("Ginger Tea", "₹20.0"));
        food_ItemList.add(new FoodItem("Cardamom Tea", "₹20.0"));
        food_ItemList.add(new FoodItem("Lemon Ice Tea", "₹60.0"));
        food_ItemList.add(new FoodItem("Masala Ice Tea", "₹80.0"));
        food_ItemList.add(new FoodItem("Honey Lemon Tea", "₹80.0"));
        food_ItemList.add(new FoodItem("Regular Coffee", "₹20.0"));
        food_ItemList.add(new FoodItem("Chocolate Coffee", "₹25.0"));
        food_ItemList.add(new FoodItem("Hot Mocha", "₹50.0"));
        food_ItemList.add(new FoodItem("Pani Puri", "₹40.0"));
        food_ItemList.add(new FoodItem("Dahi Puri", "₹60.0"));
        food_ItemList.add(new FoodItem("Dahi Bhalla", "₹60.0"));
        food_ItemList.add(new FoodItem("Raj Kachori", "₹80.0"));
        food_ItemList.add(new FoodItem("Aloo Tikki", "₹50.0"));
        food_ItemList.add(new FoodItem("Vada Pav", "₹50.0"));
        food_ItemList.add(new FoodItem("Papdi Chat", "₹50.0"));
        food_ItemList.add(new FoodItem("Sahi Kachori", "₹30.0"));
        food_ItemList.add(new FoodItem("Aloo Mutter Samosa", "₹20.0"));
        food_ItemList.add(new FoodItem("Masala Paneer Samosa", "₹30.0"));
        food_ItemList.add(new FoodItem("Cheese Corn Samosa", "₹40.0"));
        food_ItemList.add(new FoodItem("Pav Bhaji", "₹80.0"));
        food_ItemList.add(new FoodItem("Veg Burger", "₹60.0"));
        food_ItemList.add(new FoodItem("Veg Cheese Burger", "₹80.0"));
        food_ItemList.add(new FoodItem("Veg Paneer Cheese Burger", "₹100.0"));
        food_ItemList.add(new FoodItem("Loaded Sandwich", "₹80.0"));
        food_ItemList.add(new FoodItem("Kullhad Pizza", "₹100.0"));
        food_ItemList.add(new FoodItem("Indori Hot Dog", "₹60.0"));
        food_ItemList.add(new FoodItem("Veg Hot Dog", "₹70.0"));
        food_ItemList.add(new FoodItem("Paneer Hot Dog", "₹80.0"));
        food_ItemList.add(new FoodItem("Paneer Cheese Hot Dog", "₹90.0"));
        food_ItemList.add(new FoodItem("Mini Idli", "₹50.0"));
        food_ItemList.add(new FoodItem("Fried Idli", "₹50.0"));
        food_ItemList.add(new FoodItem("Idli 65", "₹50.0"));
        food_ItemList.add(new FoodItem("Podi Idli", "₹80.0"));
        food_ItemList.add(new FoodItem("Vada Sambhar", "₹50.0"));
        food_ItemList.add(new FoodItem("Dosa Plain", "₹80.0"));
        food_ItemList.add(new FoodItem("Masala Dosa", "₹95.0"));
        food_ItemList.add(new FoodItem("Jini Dosa", "₹110.0"));
        food_ItemList.add(new FoodItem("Corn Cheese Dosa", "₹110.0"));
        food_ItemList.add(new FoodItem("Paneer Masala Dosa", "₹110.0"));
        food_ItemList.add(new FoodItem("Veg Steam Momos", "₹80.0"));
        food_ItemList.add(new FoodItem("Veg Fried Momos", "₹100.0"));
        food_ItemList.add(new FoodItem("Paneer Steam Momos", "₹100.0"));
        food_ItemList.add(new FoodItem("Paneer Fried Momos", "₹120.0"));
        food_ItemList.add(new FoodItem("Cheese Corn Steam Momos", "₹100.0"));
        food_ItemList.add(new FoodItem("Cheese Corn Fried Momos", "₹120.0"));
        food_ItemList.add(new FoodItem("Poha Jalebi", "₹30.0"));
        food_ItemList.add(new FoodItem("Chole Kulche", "₹120.0"));
        food_ItemList.add(new FoodItem("Paneer Kulche", "₹150.0"));
        food_ItemList.add(new FoodItem("Chole Rice", "₹80.0"));
        food_ItemList.add(new FoodItem("Mint Mojito", "₹80.0"));
        food_ItemList.add(new FoodItem("Green Apple Soda", "₹80.0"));
        food_ItemList.add(new FoodItem("Blue Berry", "₹80.0"));
        food_ItemList.add(new FoodItem("Mango Spicy", "₹80.0"));
        food_ItemList.add(new FoodItem("Kiwi Punch", "₹80.0"));
        food_ItemList.add(new FoodItem("Mandarin", "₹80.0"));
        food_ItemList.add(new FoodItem("Watermellon Mojito", "₹100.0"));
        food_ItemList.add(new FoodItem("Seasonal Fruit Juice", "₹60.0"));
        food_ItemList.add(new FoodItem("Coconut Water", "₹50.0"));
        food_ItemList.add(new FoodItem("Penut Butter Smoothie", "₹120.0"));
        food_ItemList.add(new FoodItem("Black Current Smoothie", "₹100.0"));
        food_ItemList.add(new FoodItem("Cold Coffee", "₹80.0"));
        food_ItemList.add(new FoodItem("Rose Coffee", "₹100.0"));
        food_ItemList.add(new FoodItem("Strawberry Shake", "₹85.0"));
        food_ItemList.add(new FoodItem("Black Current Shake", "₹80.0"));
        food_ItemList.add(new FoodItem("Banana Shake", "₹80.0"));
        food_ItemList.add(new FoodItem("Sitafal Shake", "₹80.0"));
        food_ItemList.add(new FoodItem("Mango Shake", "₹100.0"));
        food_ItemList.add(new FoodItem("Chiku Shake", "₹80.0"));
        food_ItemList.add(new FoodItem("Papaya Shake", "₹80.0"));
        food_ItemList.add(new FoodItem("Fruit Punch Shake", "₹110.0"));
        food_ItemList.add(new FoodItem("Lassi", "₹60.0"));
        food_ItemList.add(new FoodItem("Mango Lassi", "₹80.0"));
        food_ItemList.add(new FoodItem("Chocolate Lassi", "₹80.0"));
        food_ItemList.add(new FoodItem("Rose Lassi", "₹80.0"));
        food_ItemList.add(new FoodItem("Kesar Lassi", "₹80.0"));
        food_ItemList.add(new FoodItem("Butter Milk", "₹40.0"));
        food_ItemList.add(new FoodItem("Gulab Jamun", "₹20.0"));
        food_ItemList.add(new FoodItem("Rasmalai", "₹30.0"));
        food_ItemList.add(new FoodItem("Raj Bhog", "₹35.0"));
        food_ItemList.add(new FoodItem("Chena Kheer", "₹60.0"));
        food_ItemList.add(new FoodItem("Sitafal Rabdi", "₹60.0"));
        food_ItemList.add(new FoodItem("Mango Rabdi", "₹60.0"));
        food_ItemList.add(new FoodItem("Rabdi", "₹50.0"));
        food_ItemList.add(new FoodItem("Laddu", "₹20.0"));
        food_ItemList.add(new FoodItem("Halwa Seasonal", "₹50.0"));
        food_ItemList.add(new FoodItem("Bengali Sweet", "₹50.0"));
        food_ItemList.add(new FoodItem("Waffels", "₹80.0"));
        food_ItemList.add(new FoodItem(" ", " "));



        // Add more food items as needed

        Food_Adapter food_Adapter = new Food_Adapter(food_ItemList);
        recyclerView.setAdapter(food_Adapter);
    }


    // Define the model class for food items
    static class FoodItem {
        private final String name;
        private final String price;

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
