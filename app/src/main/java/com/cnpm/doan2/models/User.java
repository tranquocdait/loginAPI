package com.cnpm.doan2.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class User {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("gender")
    @Expose
    private boolean gender;

    @SerializedName("role")
    @Expose
    private String role;

//    @SerializedName("nationality")
//    @Expose
//    private String nationality;

    //    @SerializedName("birthDate")
//    @Expose
//    private LocalDate birthDate;

    public User() {
        super();
    }

    public User(Integer id, String userName, String password, String fullname, boolean gender, LocalDate birthDate, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
//        this.birthDate = birthDate;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
}
