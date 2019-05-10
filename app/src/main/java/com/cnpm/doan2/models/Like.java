package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Like {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tourist")
    @Expose
    private Tourist tourist;

    public Like() {
    }

    public Like(Integer id, Tourist tourist) {
        this.id = id;
        this.tourist = tourist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }
}
