package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentPlace {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("tourist")
    @Expose
    private Tourist tourist;

    public CommentPlace() {
    }

    public CommentPlace(Integer id, String content, Tourist tourist) {
        this.id = id;
        this.content = content;
        this.tourist = tourist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }
}
