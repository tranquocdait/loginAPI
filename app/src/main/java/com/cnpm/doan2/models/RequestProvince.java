package com.cnpm.doan2.models;

public class RequestProvince {
    private String nameCategory;

    public RequestProvince() {
    }

    public RequestProvince(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
