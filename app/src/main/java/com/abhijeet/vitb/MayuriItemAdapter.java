package com.abhijeet.vitb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MayuriItemAdapter extends RecyclerView.Adapter<MayuriItemViewHolder> {

    private List<MayuriItem> mayuriItemList;
    private Context context;

    public MayuriItemAdapter(List<MayuriItem> mayuriItemList, Context context){
        this.mayuriItemList = mayuriItemList;
        this.context = context;
    }
    @NonNull
    @Override
    public MayuriItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mayuri, parent, false);
        return new MayuriItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MayuriItemViewHolder holder, int position) {
        MayuriItem mayuriItem = mayuriItemList.get(position);
        holder.textName.setText(mayuriItem.getName());
        holder.textPrice.setText(mayuriItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return mayuriItemList.size();
    }

}
