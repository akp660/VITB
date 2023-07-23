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
        foodItemList.add(new FoodItem("Veg Plait", "₹45.0"));
        foodItemList.add(new FoodItem("Veg Sub Roll", "₹42.0"));
        foodItemList.add(new FoodItem("Veg Turnover", "₹45.0"));
        foodItemList.add(new FoodItem("Chilly Cheese Plait", "₹50.0"));
        foodItemList.add(new FoodItem("Paneer Pizza Burn", "₹50.0"));
        foodItemList.add(new FoodItem("Veg Crispy Roll", "₹50.0"));
        foodItemList.add(new FoodItem("Chicken Turnover", "₹50.0"));
        foodItemList.add(new FoodItem("Chicken Pizza Bun", "₹50.0"));
        foodItemList.add(new FoodItem("Chicken Sub Roll", "₹50.0"));
        foodItemList.add(new FoodItem("Chicken Plait", "₹53.0"));
        foodItemList.add(new FoodItem("Egg Turnover", "₹40.0"));
        foodItemList.add(new FoodItem("Veg Noodles", "₹150.0"));
        foodItemList.add(new FoodItem("Veg Fried Rice", "₹130.0"));
        foodItemList.add(new FoodItem("Chicken Noodles", "₹150.0"));
        foodItemList.add(new FoodItem("Chicken Fried Rice", "₹150.0"));
        foodItemList.add(new FoodItem("Chicken Biryani", "₹190.0"));
        foodItemList.add(new FoodItem("Jeera Rice", "₹130.0"));
        foodItemList.add(new FoodItem("Veg Biryani", "₹160.0"));
        foodItemList.add(new FoodItem("Veg Schezwan Noodles", "₹131.0"));
        foodItemList.add(new FoodItem("Schezwan Chicken Fried Rice", "₹152.0"));
        foodItemList.add(new FoodItem("Paneer Fried Rice", "₹160.0"));
        foodItemList.add(new FoodItem("Paneer Noodles", "₹160.0"));
        foodItemList.add(new FoodItem("Schezwan Chicken Noodles", "₹152.0"));
        foodItemList.add(new FoodItem("Egg Noodles", "₹142.0"));
        foodItemList.add(new FoodItem("Egg Fried Rice", "₹142.0"));
        foodItemList.add(new FoodItem("Ginger Tea", "₹15.0"));
        foodItemList.add(new FoodItem("Coffee", "₹15.0"));
        foodItemList.add(new FoodItem("Hot Chocolate", "₹40.0"));
        foodItemList.add(new FoodItem("Black Coffee", "₹30.0"));
        foodItemList.add(new FoodItem("Ginger Tea", "₹10.0"));
        foodItemList.add(new FoodItem("Butterscotch Milkshake Small", "₹50.0"));
        foodItemList.add(new FoodItem("Butterscotch Milkshake Large", "₹80.0"));
        foodItemList.add(new FoodItem("Vanilla Milkshake Small", "₹50.0"));
        foodItemList.add(new FoodItem("Vanilla Milkshake Large", "₹80.0"));
        foodItemList.add(new FoodItem("Chocolate Milkshake Small", "₹50.0"));
        foodItemList.add(new FoodItem("Chocolate Milkshake Large", "₹80.0"));
        foodItemList.add(new FoodItem("Oreo Milkshake Small", "₹50.0"));
        foodItemList.add(new FoodItem("Oreo Milkshake Large", "₹95.0"));
        foodItemList.add(new FoodItem("KitKat Milkshake Small", "₹60.0"));
        foodItemList.add(new FoodItem("KitKat Milkshake Large", "₹95.0"));
        foodItemList.add(new FoodItem("Strawberry Milkshake Small", "₹42.0"));
        foodItemList.add(new FoodItem("Strawberry Milkshake Large", "₹79.0"));
        foodItemList.add(new FoodItem("Snickers Milkshake Small", "₹50.0"));
        foodItemList.add(new FoodItem("Snickers Milkshake Large", "₹90.0"));
        foodItemList.add(new FoodItem("Cold Coffee Small", "₹50.0"));
        foodItemList.add(new FoodItem("Cold Coffee Large", "₹80.0"));
        foodItemList.add(new FoodItem("Orange Juice", "₹70.0"));
        foodItemList.add(new FoodItem("Sweet Lime juice", "₹70.0"));
        foodItemList.add(new FoodItem("Pineapple Juice", "₹70.0"));
        foodItemList.add(new FoodItem("Mango Juice", "₹70.0"));
        foodItemList.add(new FoodItem("Lemon Ice Tea Small", "₹42.0"));
        foodItemList.add(new FoodItem("Lemon Ice Tea Large", "₹70.0"));
        foodItemList.add(new FoodItem("Lime Mint Cooler Small", "₹42.0"));
        foodItemList.add(new FoodItem("Lime Mint Cooler Large", "₹70.0"));
        foodItemList.add(new FoodItem("Masala Dosa", "₹80.0"));
        foodItemList.add(new FoodItem("Butter Masala Dosa", "₹90.0"));
        foodItemList.add(new FoodItem("Plain Dosa", "₹70.0"));
        foodItemList.add(new FoodItem("Ghee Dosa", "₹79.0"));
        foodItemList.add(new FoodItem("Butter Dosa", "₹80.0"));
        foodItemList.add(new FoodItem("Podi Dosa", "₹80.0"));
        foodItemList.add(new FoodItem("Mushroom Kheema Dosa", "₹100.0"));
        foodItemList.add(new FoodItem("Paper Masala Dosa", "₹70.0"));
        foodItemList.add(new FoodItem("Paneer Kheema Dosa", "₹105.0"));
        foodItemList.add(new FoodItem("Cheese Masala Dosa", "₹100.0"));
        foodItemList.add(new FoodItem("Chilly Cheese Garlic Dosa", "₹130.0"));
        foodItemList.add(new FoodItem("Onion Cheese Garlic Dosa", "₹100.0"));
        foodItemList.add(new FoodItem("Chilly Garlic Paneer Dosa", "₹116.0"));
        foodItemList.add(new FoodItem("Ghee Sambar Idlies", "₹60.0"));
        foodItemList.add(new FoodItem("Double Cheese Sandwich", "₹89.0"));
        foodItemList.add(new FoodItem("Double CheeseSandwich + French Fries", "₹137.0"));
        foodItemList.add(new FoodItem("Sweet Corn Sandwich", "₹89.0"));
        foodItemList.add(new FoodItem("Sweet Corn Sandwich + French Fries", "₹126.0"));
        foodItemList.add(new FoodItem("Paneer Tikka Sandwich", "₹100.0"));
        foodItemList.add(new FoodItem("Paneer Tikka Sandwich + French Fries", "₹152.0"));
        foodItemList.add(new FoodItem("Chicken Tikka Sandwich", "₹121.0"));
        foodItemList.add(new FoodItem("Chicken Tikka + French Fries", "₹163.0"));
        foodItemList.add(new FoodItem("Mushroom Cheese Sandwich", "₹100.0"));
        foodItemList.add(new FoodItem("Mushroom Cheese Sandwich + French Fries ", "₹152.0"));
        foodItemList.add(new FoodItem("Classic Chicken Club Sandwich", "₹121.0"));
        foodItemList.add(new FoodItem("Classic Chicken Club Sandwich + French Fries", "₹152.0"));
        foodItemList.add(new FoodItem("Cheese Mayo Grilled Chicken Sandwich ", "₹110.0"));
        foodItemList.add(new FoodItem("Cheese Mayo Grilled Chicken Sandwich + French Fries", "₹152.0"));
        foodItemList.add(new FoodItem("Bombay Grilled Sandwich", "₹68.0"));
        foodItemList.add(new FoodItem("Bombay Grilled Sandwich + French Fries", "₹137.0"));
        foodItemList.add(new FoodItem("Veg Cheese Burger", "₹95.0"));
        foodItemList.add(new FoodItem("Veg Cheese Burger + French Fries", "₹152.0"));
        foodItemList.add(new FoodItem("Chicken Burger", "₹116.0"));
        foodItemList.add(new FoodItem("Chicken Burger + French Fries", "₹163.0"));
        foodItemList.add(new FoodItem("Veg Wrap", "₹95.0"));
        foodItemList.add(new FoodItem("Veg Wrap + French Fries ", "₹147.0"));
        foodItemList.add(new FoodItem("Chicken Wrap", "₹121.0"));
        foodItemList.add(new FoodItem("Chicken Wrap + French Fries", "₹158.0"));
        foodItemList.add(new FoodItem("Paneer Wrap", "₹100.0"));
        foodItemList.add(new FoodItem("Paneer Wrap + French Fries", "₹152.0"));
        foodItemList.add(new FoodItem("Paneer Butter Masala", "₹158.0"));
        foodItemList.add(new FoodItem("Kadai Butter Masala", "₹147.0"));
        foodItemList.add(new FoodItem("Paneer Tikka Masala", "₹158.0"));
        foodItemList.add(new FoodItem("Shahi Paneer Masala", "₹147.0"));
        foodItemList.add(new FoodItem("Chicken Tikka Masala", "₹168.0"));
        foodItemList.add(new FoodItem("Schezwan Chicken Gravy", "₹168.0"));
        foodItemList.add(new FoodItem("Dal Makhani", "₹150.0"));
        foodItemList.add(new FoodItem("Dal Tadka", "₹130.0"));
        foodItemList.add(new FoodItem("Kadai Sabzi", "140.0"));
        foodItemList.add(new FoodItem("Punjabi Chicken Masala", "₹168.0"));
        foodItemList.add(new FoodItem("Hyderabadi Chicken MAsala", "₹168.0"));
        foodItemList.add(new FoodItem("Butter Chicken Masala", "₹168.0"));
        foodItemList.add(new FoodItem("Chicken Lababdar", "₹168.0"));
        foodItemList.add(new FoodItem("Dhanya Murga Adraki Masala", "₹168.0"));
        foodItemList.add(new FoodItem("Chicken Keema Masala", "₹168.0"));
        foodItemList.add(new FoodItem("Tandoori Roti", "₹30.0"));
        foodItemList.add(new FoodItem("Tandoori Butter Roti", "₹30.0"));
        foodItemList.add(new FoodItem("Tandoori Aloo Paratha", "₹55.0"));
        foodItemList.add(new FoodItem("Tandoori Lachha Paratha", "₹47.0"));
        foodItemList.add(new FoodItem("Naan", "₹37.0"));
        foodItemList.add(new FoodItem("Butter Naan", "₹42.0"));
        foodItemList.add(new FoodItem("Garlic Naan", "₹45.0"));
        foodItemList.add(new FoodItem("Classic French Fries", "₹95.0"));
        foodItemList.add(new FoodItem("Masala French Fries", "₹105.0"));
        foodItemList.add(new FoodItem("Veg Cheese Fingers", "₹115.0"));
        foodItemList.add(new FoodItem("Paneer Tikka", "₹158.0"));
        foodItemList.add(new FoodItem("Paneer Malai Tikka", "₹158.0"));
        foodItemList.add(new FoodItem("Chilly Paneer Dry", "₹130.0"));
        foodItemList.add(new FoodItem("Chilly Paneer Gravy", "₹131.0"));
        foodItemList.add(new FoodItem("Veg Manchurian", "₹137.0"));
        foodItemList.add(new FoodItem("Mushroom Chilly", "₹137.0"));
        foodItemList.add(new FoodItem("Paneer 65", "₹140.0"));
        foodItemList.add(new FoodItem("Mushroom 65", "₹140.0"));
        foodItemList.add(new FoodItem("Honey Chilly Potato", "₹140.0"));
        foodItemList.add(new FoodItem("Chicken Nuggets", "₹152.0"));
        foodItemList.add(new FoodItem("Chicken Wings", "₹152.0"));
        foodItemList.add(new FoodItem("Chiken Tikka", "₹168.0"));
        foodItemList.add(new FoodItem("Chiken Malai Kebab", "₹163.0"));
        foodItemList.add(new FoodItem("Tandoori Chicken Half", "₹210.0"));
        foodItemList.add(new FoodItem("Tandoori Chicken Full", "₹399.0"));
        foodItemList.add(new FoodItem("Chilly Chicken Dry", "₹140.0"));
        foodItemList.add(new FoodItem("Dragon Chicken", "₹152.0"));
        foodItemList.add(new FoodItem("Chicken 65", "₹152.0"));
        foodItemList.add(new FoodItem("Bread Omelette", "₹63.0"));
        foodItemList.add(new FoodItem("Masala Omelette", "₹53.0"));
        foodItemList.add(new FoodItem("Cheese Omelette", "₹53.0"));
        foodItemList.add(new FoodItem("Egg Bhurji", "₹37.0"));
        foodItemList.add(new FoodItem("Veg Momos", "₹110.0"));
        foodItemList.add(new FoodItem("Paneer Momos", "₹130.0"));
        foodItemList.add(new FoodItem("Chicken Momos", "₹140.0"));
        foodItemList.add(new FoodItem("Veg Mixed Nachos", "₹147.0"));
        foodItemList.add(new FoodItem("Chicken Mixed Nachos", "₹120.0"));
        foodItemList.add(new FoodItem("Penne Alfredo Pasta Small", "₹105.0"));
        foodItemList.add(new FoodItem("Penne Alfredo Pasta Large", "₹168.0"));
        foodItemList.add(new FoodItem("Penne Arablata Small", "₹105.0"));
        foodItemList.add(new FoodItem("Penne Arablata Large", "₹168.0"));
        foodItemList.add(new FoodItem("Pink Sauce Pasta Small", "₹105.0"));
        foodItemList.add(new FoodItem("Pink Sauce Pasta Large", "₹168.0"));
        foodItemList.add(new FoodItem("Basil Sauce Pasta Small", "₹105.0"));
        foodItemList.add(new FoodItem("Basil Sauce Pasta Large", "₹168.0"));
        foodItemList.add(new FoodItem("Penne Alfredo Chicken Pasta Small", "₹127.0"));
        foodItemList.add(new FoodItem("Penne Alfredo Chicken Pasta Large", "₹179.0"));
        foodItemList.add(new FoodItem("Penne Arablata Chicken Pasta Small", "₹127.0"));
        foodItemList.add(new FoodItem("Penne Arablata Chicken Pasta Large", "₹179.0"));
        foodItemList.add(new FoodItem("Pink Sauce Chicken Pasta Small", "₹127.0"));
        foodItemList.add(new FoodItem("Pink Sauce Chicken Pasta Large", "₹179.0"));
        foodItemList.add(new FoodItem("Basil Sauce Chicken Pasta Small", "₹127.0"));
        foodItemList.add(new FoodItem("Basil Sauce Chicken Pasta Large", "₹179.0"));
        foodItemList.add(new FoodItem("Oreo Pastry", "₹80.0"));
        foodItemList.add(new FoodItem("Kitkat Pastry", "₹80.0"));
        foodItemList.add(new FoodItem("Choco Truffle", "₹80.0"));
        foodItemList.add(new FoodItem("Blue Berry", "₹80.0"));
        foodItemList.add(new FoodItem("Swiss Roll", "₹74.0"));
        foodItemList.add(new FoodItem("Walnut Brownle", "₹74.0"));
        foodItemList.add(new FoodItem("Choco Doughnut", "₹42.0"));
        foodItemList.add(new FoodItem("Choco Truffle 500grm", "₹550.0"));
        foodItemList.add(new FoodItem("Choco Truffle 1kg", "₹1000.0"));
        foodItemList.add(new FoodItem("Kitkat Cake 500grm", "₹600.0"));
        foodItemList.add(new FoodItem("Kitkat Cake 1kg", "₹1050.0"));
        foodItemList.add(new FoodItem("Oreo Cake 500grm", "₹600.0"));
        foodItemList.add(new FoodItem("Oreo Cake 1kg", "₹1050.0"));
        foodItemList.add(new FoodItem("Chocolate Cake 500grm", "₹500.0"));
        foodItemList.add(new FoodItem("Chocolate Cake 1kg", "₹900.0"));
        foodItemList.add(new FoodItem("Butterscotch Cake 500grm", "₹500.0"));
        foodItemList.add(new FoodItem("Butterscotch Cake 1kg", "₹880.0"));
        foodItemList.add(new FoodItem(" ", " "));



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
