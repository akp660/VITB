package com.abhijeet.vitb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final List<Underbelly.FoodItem> foodItemList;

    public FoodAdapter(List<Underbelly.FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Underbelly.FoodItem foodItem = foodItemList.get(position);
        holder.foodName.setText(foodItem.getName());
        holder.foodPrice.setText(foodItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView foodPrice;

        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tv_food_name);
            foodPrice = itemView.findViewById(R.id.tv_food_price);
        }
    }
}
