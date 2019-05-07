package com.cnpm.doan2.models;

public class RequestComment {
    String content;

    public RequestComment() {
    }

    public RequestComment(String content) {
        this.content = content;
    }

    public String getData() {
        return content;
    }

    public void setData(String content) {
        this.content = content;
    }
}
