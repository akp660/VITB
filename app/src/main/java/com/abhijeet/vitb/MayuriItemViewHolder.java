package com.abhijeet.vitb;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MayuriItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textName;
    public TextView textPrice;
    public MayuriItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        textPrice = itemView.findViewById(R.id.textPrice);
    }
}

