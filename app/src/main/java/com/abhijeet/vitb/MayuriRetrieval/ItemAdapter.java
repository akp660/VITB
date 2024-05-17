package com.abhijeet.vitb.MayuriRetrieval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.vitb.R;

import java.util.List;

// ItemAdapter.java
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public TextView textPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mayuri, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textName.setText(item.getName());
        holder.textPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

//public class ItemAdapter extends RecyclerView.Adapter<MayuriItemViewHolder> {
//
//    private List<Item> mayuriItemList;
//    private Context context;
//
//    public ItemAdapter(List<Item> mayuriItemList, Context context){
//        this.mayuriItemList = mayuriItemList;
//        this.context = context;
//    }
//    @NonNull
//    @Override
//    public MayuriItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_mayuri, parent, false);
//        return new MayuriItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MayuriItemViewHolder holder, int position) {
//        Item mayuriItem = mayuriItemList.get(position);
//        holder.textName.setText(mayuriItem.getName());
//        holder.textPrice.setText(mayuriItem.getPrice());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mayuriItemList.size();
//    }
//
//}
