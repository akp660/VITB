package com.abhijeet.vitb.UnderbelyRetrieval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.vitb.MayuriRetrieval.MayuriItem;
import com.abhijeet.vitb.MayuriRetrieval.MayuriItemViewHolder;
import com.abhijeet.vitb.R;

import java.util.List;


public class UnderbelyItemAdapter extends RecyclerView.Adapter<UnderbelyItemViewHolder> {

    private List<UnderbelyItem> underbelyItemList;
    private Context context;

    public UnderbelyItemAdapter(List<UnderbelyItem> underbelyItemList, Context context){
        this.underbelyItemList = underbelyItemList;
        this.context = context;
    }
    @NonNull
    @Override
    public UnderbelyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mayuri, parent, false);
        return new UnderbelyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnderbelyItemViewHolder holder, int position) {
        UnderbelyItem underbelyItem = underbelyItemList.get(position);
        holder.textName.setText(underbelyItem.getName());
        holder.textPrice.setText(underbelyItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return underbelyItemList.size();
    }

}

