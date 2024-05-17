package com.abhijeet.vitb.MayuriRetrieval;

import java.util.List;

public class MayuriCategory {
    private String title;
    private List<MayuriItem> items;
    private boolean selected;

    public MayuriCategory(String title, List<MayuriItem> items) {
        this.title = title;
        this.items = items;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public List<MayuriItem> getItems() {
        return items;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
