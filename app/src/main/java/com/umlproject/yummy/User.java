package com.umlproject.yummy;

public class User {
    private String email,pwd,name;

    public User(){
    }
    public User(String name,String email, String pwd){
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public String getPwd(){
        return pwd;
    }
    public String getName(){return name; }
}
