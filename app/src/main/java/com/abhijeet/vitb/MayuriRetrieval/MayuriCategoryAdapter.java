package com.abhijeet.vitb.MayuriRetrieval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.vitb.R;

import java.util.List;

public class MayuriCategoryAdapter extends RecyclerView.Adapter<MayuriCategoryAdapter.MayuriCategoryViewHolder>{

    private List<MayuriCategory> mayuriCategoryList;
    private Context context;

    public MayuriCategoryAdapter (List<MayuriCategory> mayuriCategoryList, Context context){
        this.mayuriCategoryList = mayuriCategoryList;
        this.context = context;
    }
    @NonNull
    @Override
    public MayuriCategoryAdapter.MayuriCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mayuri_category, parent, false);
        return new MayuriCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MayuriCategoryAdapter.MayuriCategoryViewHolder holder, int position) {
        MayuriCategory mayuriCategory = mayuriCategoryList.get(position);
        holder.textCategoryName.setText(mayuriCategory.getCategoryName());

        MayuriItemAdapter mayuriItemAdapter = new MayuriItemAdapter(mayuriCategory.getItems(), context);
        holder.recyclerViewItems.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerViewItems.setAdapter(mayuriItemAdapter);
    }

    @Override
    public int getItemCount() {
        return mayuriCategoryList.size();
    }

    public static class MayuriCategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView textCategoryName;
        public RecyclerView recyclerViewItems;
        public MayuriCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textCategoryName = itemView.findViewById(R.id.textCategoryName);
            recyclerViewItems = itemView.findViewById(R.id.recyclerViewItems);
        }
    }
}