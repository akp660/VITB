package com.abhijeet.vitb.UnderbelyRetrieval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.vitb.MayuriRetrieval.MayuriCategory;
import com.abhijeet.vitb.MayuriRetrieval.MayuriItemAdapter;
import com.abhijeet.vitb.R;

import java.util.List;

public class UnderbelyCategoryAdapter extends RecyclerView.Adapter<com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategoryAdapter.UnderbelyCategoryViewHolder>{

    private List<UnderbelyCategory> UnderbelyCategoryList;
    private Context context;

    public UnderbelyCategoryAdapter (List<UnderbelyCategory> UnderbelyCategoryList, Context context){
        this.UnderbelyCategoryList = UnderbelyCategoryList;
        this.context = context;
    }
    @NonNull
    @Override
    public com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategoryAdapter.UnderbelyCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_underbely_category, parent, false);
        return new com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategoryAdapter.UnderbelyCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.abhijeet.vitb.UnderbelyRetrieval.UnderbelyCategoryAdapter.UnderbelyCategoryViewHolder holder, int position) {
        UnderbelyCategory UnderbelyCategory = UnderbelyCategoryList.get(position);
        holder.textCategoryName.setText(UnderbelyCategory.getCategoryName());

        UnderbelyItemAdapter underbelyItemAdapter = new UnderbelyItemAdapter(UnderbelyCategory.getItems(), context);
        holder.recyclerViewItems.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerViewItems.setAdapter(underbelyItemAdapter);
    }

    @Override
    public int getItemCount() {
        return UnderbelyCategoryList.size();
    }

    public static class UnderbelyCategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView textCategoryName;
        public RecyclerView recyclerViewItems;
        public UnderbelyCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.textCategoryName);
            recyclerViewItems = itemView.findViewById(R.id.recyclerViewItems);
        }
    }
}