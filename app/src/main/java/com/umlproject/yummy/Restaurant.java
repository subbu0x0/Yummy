package com.umlproject.yummy;

public class Restaurant {
    private String name,pwd,mail;

    public Restaurant() {
    }

    public Restaurant(String name, String pwd, String mail) {
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
    }

    public String getName1() {
        return name;
    }

    public void setName1(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
