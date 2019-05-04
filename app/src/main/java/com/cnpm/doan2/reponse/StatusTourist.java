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
}
