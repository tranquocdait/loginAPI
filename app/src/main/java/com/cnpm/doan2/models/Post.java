package com.cnpm.doan2.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("images")
    @Expose
    private List<Image> images;

    @SerializedName("place")
    @Expose
    private Place place;

    @SerializedName("tourist")
    @Expose
    private Tourist tourist;

//    @SerializedName("createdAt")
//    @Expose
//    private int[] createdAt;
//
//    @SerializedName("updatedAt")
//    @Expose
//    private int[] updatedAt;
//
//    @SerializedName("likes")
//    @Expose
//    private int[] likes;

    public Post() {
    }

    public Post(Integer id, String content, List<Image> images, Place place, Tourist tourist, int[] createdAt, int[] updatedAt, int[] likes) {
        this.id = id;
        this.content = content;
        this.images = images;
        this.place = place;
        this.tourist = tourist;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.likes = likes;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

//    public int[] getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(int[] createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public int[] getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(int[] updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public int[] getLikes() {
//        return likes;
//    }
//
//    public void setLikes(int[] likes) {
//        this.likes = likes;
//    }
}
