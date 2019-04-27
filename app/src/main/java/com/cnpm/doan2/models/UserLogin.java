package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin {
//    @SerializedName("userName")
//    @Expose
    private String userName;

//    @SerializedName("password")
//    @Expose
    private String password;

    public UserLogin() {
    }

    public UserLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
