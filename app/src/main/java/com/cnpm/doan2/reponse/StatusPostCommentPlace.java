package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.CommentPlace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusPostCommentPlace {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private CommentPlace data;

    public StatusPostCommentPlace() {
    }

    public StatusPostCommentPlace(String status, CommentPlace data) {
        this.status = status;
        this.data = data;
    }

}
