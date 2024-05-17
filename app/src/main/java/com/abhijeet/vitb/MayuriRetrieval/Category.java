package com.abhijeet.vitb.MayuriRetrieval;

import java.util.List;

public class Category {
    private String title;
    private List<Item> items;
    private boolean selected;

    public Category(String title, List<Item> items) {
        this.title = title;
        this.items = items;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
