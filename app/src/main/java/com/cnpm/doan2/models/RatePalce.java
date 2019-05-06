package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatePalce {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("numberStar")
    @Expose
    private Integer numberStar;
    @SerializedName("touristId")
    @Expose
    private Integer touristId;

    public RatePalce() {
    }

    public RatePalce(Integer id, Integer numberStar, Integer touristId) {
        this.id = id;
        this.numberStar = numberStar;
        this.touristId = touristId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(Integer numberStar) {
        this.numberStar = numberStar;
    }

    public Integer getTouristId() {
        return touristId;
    }

    public void setTouristId(Integer touristId) {
        this.touristId = touristId;
    }
}
