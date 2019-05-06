package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.CommentPlace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusCommentPlace {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<CommentPlace> data;

    public StatusCommentPlace() {
    }

    public StatusCommentPlace(String status, List<CommentPlace> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommentPlace> getData() {
        return data;
    }

    public void setData(List<CommentPlace> data) {
        this.data = data;
    }

}
