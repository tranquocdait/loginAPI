package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.Post;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusPost {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Post> data;

    public StatusPost() {
    }

    public StatusPost(String status, List<Post> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }
}
