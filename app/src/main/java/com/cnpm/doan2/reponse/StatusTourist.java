package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.Tourist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusTourist {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private Tourist data;

    public StatusTourist() {
    }

    public StatusTourist(String status, Tourist data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tourist getData() {
        return data;
    }

    public void setData(Tourist data) {
        this.data = data;
    }
}
