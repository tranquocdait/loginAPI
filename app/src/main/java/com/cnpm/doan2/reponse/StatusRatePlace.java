package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.RatePalce;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusRatePlace {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<RatePalce> data;

    public StatusRatePlace() {
    }

    public StatusRatePlace(String status, List<RatePalce> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RatePalce> getData() {
        return data;
    }

    public void setData(List<RatePalce> data) {
        this.data = data;
    }
}
