package com.cnpm.doan2.service;

import com.cnpm.doan2.models.Place;
import com.cnpm.doan2.models.Post;
import com.cnpm.doan2.reponse.StatusPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {
    @GET("tourists/{touristId}/posts")
    Call<StatusPost> getListPost(@Path("touristId") String touristId);
}
