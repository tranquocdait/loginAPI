package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Tourist {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("fullname")
    @Expose
    private String fullname;

//    @SerializedName("gender")
//    @Expose
//    private boolean gender;

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
        this.fullname = fullname;
 //       this.gender = gender;
        this.role = role;
        this.avatar = avatar;
        this.nationality=nationality;
//        this.birthDate = birthDate;
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
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

//    public boolean isGender() {
//        return gender;
//    }
//
//    public void setGender(boolean gender) {
//        this.gender = gender;
//    }

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
