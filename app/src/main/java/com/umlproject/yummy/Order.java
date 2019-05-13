package com.umlproject.yummy;

public class Order {
    private String rest,uname,price,paymode,status,location,id,tracking;

    public Order() {
    }

    public Order(String tracking,String id,String rest, String uname, String price, String paymode, String status, String location) {
        this.rest = rest;
        this.tracking = tracking;
        this.id = id;
        this.uname = uname;
        this.price = price;
        this.paymode = paymode;
        this.status = status;
        this.location = location;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
