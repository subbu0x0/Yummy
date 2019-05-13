package com.umlproject.yummy;

public class Items{
    private String restaurant,name;
    private long price;
    public Items(){

    }
    public Items(String restaurant, String name, long price){
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }
    public String getRestaurant(){
        return restaurant;
    }
    public String getName(){
        return name;
    }
    public long getPrice(){
        return price;
    }
}
