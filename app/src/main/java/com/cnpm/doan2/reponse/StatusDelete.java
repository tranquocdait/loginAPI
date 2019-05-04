package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.Delete;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusDelete {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private Delete data;
}
