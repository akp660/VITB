package com.abhijeet.vitb.UnderbelyRetrieval;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.vitb.R;

public class UnderbelyItemViewHolder  extends RecyclerView.ViewHolder {
    public TextView textName;
    public TextView textPrice;
    public UnderbelyItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        textPrice = itemView.findViewById(R.id.textPrice);
    }
}


