package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Follow {
    @SerializedName("allFollowers")
    @Expose
    private Integer[] allFollowers;
    @SerializedName("allFollowings")
    @Expose
    private Integer[] allFollowings;

    public Follow() {
    }

    public Follow(Integer[] allFollowers, Integer[] allFollowings) {
        this.allFollowers = allFollowers;
        this.allFollowings = allFollowings;
    }

    public Integer[] getAllFollowers() {
        return allFollowers;
    }

    public void setAllFollowers(Integer[] allFollowers) {
        this.allFollowers = allFollowers;
    }

    public Integer[] getAllFollowings() {
        return allFollowings;
    }

    public void setAllFollowings(Integer[] allFollowings) {
        this.allFollowings = allFollowings;
    }
}
