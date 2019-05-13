package com.umlproject.yummy;

public class Cart {
    private String name,restaurant;
    private long price;

    public Cart() {
    }

    public Cart(String name, long price,String restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
     public  String getRestaurant(){ return restaurant;}

}
