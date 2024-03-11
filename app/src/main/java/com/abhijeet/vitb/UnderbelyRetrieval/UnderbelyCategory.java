package com.abhijeet.vitb.UnderbelyRetrieval;

import com.abhijeet.vitb.Fragments.Underbely;

import java.util.List;

public class UnderbelyCategory {
    private String categoryName;
    private List<UnderbelyItem> items;

    public UnderbelyCategory() {

    }
    public UnderbelyCategory(String categoryName, List<UnderbelyItem> items){
        this.categoryName = categoryName;
        this.items=items;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<UnderbelyItem> getItems() {
        return items;
    }

    public void setItems(List<UnderbelyItem> items) {
        this.items = items;
    }
}
