package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.Follow;
import com.cnpm.doan2.models.Post;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusFollow {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Follow data;

    public StatusFollow() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Follow getData() {
        return data;
    }

    public void setData(Follow data) {
        this.data = data;
    }

    public StatusFollow(String status, Follow data) {
        this.status = status;
        this.data = data;
    }
}
