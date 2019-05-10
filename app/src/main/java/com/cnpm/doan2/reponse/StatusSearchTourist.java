package com.cnpm.doan2.reponse;

import com.cnpm.doan2.models.Tourist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusSearchTourist {
    @SerializedName("")
    @Expose
    private List<Tourist> touristList;

    public StatusSearchTourist(List<Tourist> touristList) {
        this.touristList = touristList;
    }

    public StatusSearchTourist() {
    }

    public List<Tourist> getTouristList() {
        return touristList;
    }

    public void setTouristList(List<Tourist> touristList) {
        this.touristList = touristList;
    }
}
