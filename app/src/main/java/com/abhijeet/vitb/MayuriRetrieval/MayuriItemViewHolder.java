package com.abhijeet.vitb.MayuriRetrieval;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.vitb.R;

public class MayuriItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textName;
    public TextView textPrice;
    public MayuriItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        textPrice = itemView.findViewById(R.id.textPrice);
    }
}

