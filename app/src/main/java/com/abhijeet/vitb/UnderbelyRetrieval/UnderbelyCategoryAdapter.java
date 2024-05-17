package com.abhijeet.vitb.UnderbelyRetrieval;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abhijeet.vitb.R;
import java.util.List;

public class UnderbelyCategoryAdapter extends RecyclerView.Adapter<UnderbelyCategoryAdapter.CategoryViewHolder> {
    private List<UnderbelyCategory> categoryList;
    private OnCategoryClickListener categoryClickListener;

    public interface OnCategoryClickListener {
        void onCategoryClick(UnderbelyCategory category);
    }

    public UnderbelyCategoryAdapter(List<UnderbelyCategory> categoryList, OnCategoryClickListener categoryClickListener) {
        this.categoryList = categoryList;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        UnderbelyCategory category = categoryList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);

            itemView.setOnClickListener(v -> {
                for (UnderbelyCategory category : categoryList) {
                    category.setSelected(false);
                }
                categoryList.get(getAdapterPosition()).setSelected(true);
                notifyDataSetChanged();
                categoryClickListener.onCategoryClick(categoryList.get(getAdapterPosition()));
            });
        }

        public void bind(UnderbelyCategory category) {
            // Load image using your preferred image loading library (e.g., Glide or Picasso)
            // Glide.with(itemView).load(category.getImageUrl()).into(categoryImage);
            categoryTitle.setText(category.getTitle());

            if (category.isSelected()) {
                itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.light4));
            } else {
                itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.white));
            }
        }
    }
}
