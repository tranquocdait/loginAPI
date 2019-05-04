package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class RequestTourist {
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

    @SerializedName("birthDate")
    @Expose
    private LocalDate birthDate;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    public RequestTourist() {
    }

    public RequestTourist(String userName, String password, String fullname, boolean gender, LocalDate birthDate, String nationality) {
        this.userName = userName;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.nationality = nationality;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
