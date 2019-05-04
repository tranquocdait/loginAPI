package com.cnpm.doan2.models;

public class RequestCategory {
    private String nameCategory;

    public RequestCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public RequestCategory() {
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
