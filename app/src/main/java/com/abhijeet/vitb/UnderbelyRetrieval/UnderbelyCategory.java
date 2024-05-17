package com.abhijeet.vitb.UnderbelyRetrieval;

import java.util.List;

public class UnderbelyCategory {
    private String title;
    private List<UnderbelyItem> items;
    private boolean selected;

    public UnderbelyCategory(String title, List<UnderbelyItem> items) {
        this.title = title;
        this.items = items;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public List<UnderbelyItem> getItems() {
        return items;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
