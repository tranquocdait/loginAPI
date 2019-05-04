package com.cnpm.doan2.models;

public class RequestChangeAvatar {
    private String url;

    public RequestChangeAvatar() {
    }

    public RequestChangeAvatar(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
