package com.abhijeet.vitb.MayuriRetrieval;

public class Item {
    private String name;
    private String price;

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}

//public class Item {
//    private String name;
//    private String price;
//
//    public Item(){
//
//    }
//
//    public Item(String name, String price){
//        this.name = name;
//        this.price = price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//}

