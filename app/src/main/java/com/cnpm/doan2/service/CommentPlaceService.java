package com.cnpm.doan2.service;

import com.cnpm.doan2.reponse.StatusCommentPlace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommentPlaceService {
    @GET("places/{id}/comments")
    Call<StatusCommentPlace> getCommentPlace(@Path("id") String id);
}
