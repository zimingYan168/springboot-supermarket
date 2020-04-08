package com.springboot.supermarket.bean;/*
 @author yanziming
 @date 2020/4/7 - 16:07
 */

public class ShopUser {

    private String id;
    private String useremail;
    private String password;
    private boolean admin;
    private boolean activation; //是否激活



    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    @Override
    public String toString() {
        return "ShopUser{" +
                "id='" + id + '\'' +
                ", useremail='" + useremail + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
