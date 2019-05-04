package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delete {
    @SerializedName("delete")
    @Expose
    private String delete;

    public Delete() {
    }

    public Delete(String delete) {
        this.delete = delete;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
