package com.abhijeet.vitb.MayuriRetrieval;

import java.util.List;

public class MayuriCategory {
    private String categoryName;
    private List<MayuriItem> items;

    public MayuriCategory(){

    }
    public MayuriCategory(String categoryName, List<MayuriItem> items){
        this.categoryName = categoryName;
        this.items=items;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<MayuriItem> getItems() {
        return items;
    }

    public void setItems(List<MayuriItem> items) {
        this.items = items;
    }
}
