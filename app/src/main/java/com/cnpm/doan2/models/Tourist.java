package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Tourist {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("gender")
    @Expose
    private Boolean gender;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("image")
    @Expose
    private Image avatar;

//    @SerializedName("birthDate")
//    @Expose
//    private LocalDate birthDate;

    public Tourist() {
    }

    public Tourist(Integer id, String fullname, boolean gender, String role,String nationality, Image avatar, LocalDate birthDate) {
        this.id = id;
        this.fullName = fullname;
        this.gender = gender;
        this.role = role;
        this.avatar = avatar;
        this.nationality=nationality;
//        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
}
