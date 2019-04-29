package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("namePlace")
    @Expose
    private String namePlace;

    @SerializedName("about")
    @Expose
    private String about;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("longitude")
    @Expose
    private Double longitude;

    @SerializedName("images")
    @Expose
    private List<Image> images;

    @SerializedName("province")
    @Expose
    private Province province;

    @SerializedName("category")
    @Expose
    private Category category;

    public Place() {
    }

    public Place(Integer id, String namePlace,
                 String about, String address,
                 Double latitude, Double longitude,
                 List<Image> images, Province province,
                 Category category) {
        this.id = id;
        this.namePlace = namePlace;
        this.about = about;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.images = images;
        this.province = province;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
