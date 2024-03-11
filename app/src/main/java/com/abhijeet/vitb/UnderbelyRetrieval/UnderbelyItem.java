package com.abhijeet.vitb.UnderbelyRetrieval;

public class UnderbelyItem {
    private String name;
    private String price;

    public UnderbelyItem(){

    }

    public UnderbelyItem(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}


