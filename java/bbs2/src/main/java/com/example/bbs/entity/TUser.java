package com.example.bbs.entity;


public class TUser {

    private long id;
    private String account;
    private String username;
    private String password;
    private java.sql.Timestamp registerTime;
    private String introduce;
    private String image;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public java.sql.Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(java.sql.Timestamp registerTime) {
        this.registerTime = registerTime;
    }


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
