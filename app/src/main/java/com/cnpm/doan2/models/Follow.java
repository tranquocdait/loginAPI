package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Follow {
    @SerializedName("allFollowers")
    @Expose
    private List<Tourist> allFollowers;
    @SerializedName("allFollowings")
    @Expose
    private List<Tourist> allFollowings;

    public Follow() {
    }

    public Follow(List<Tourist> allFollowers, List<Tourist> allFollowings) {
        this.allFollowers = allFollowers;
        this.allFollowings = allFollowings;
    }

    public List<Tourist> getAllFollowers() {
        return allFollowers;
    }

    public void setAllFollowers(List<Tourist> allFollowers) {
        this.allFollowers = allFollowers;
    }

    public List<Tourist> getAllFollowings() {
        return allFollowings;
    }

    public void setAllFollowings(List<Tourist> allFollowings) {
        this.allFollowings = allFollowings;
    }
}
