package com.cnpm.doan2.service;

import com.cnpm.doan2.models.RequestComment;
import com.cnpm.doan2.reponse.StatusCommentPlace;
import com.cnpm.doan2.reponse.StatusPostCommentPlace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentPlaceService {
    @GET("places/{id}/comments")
    Call<StatusCommentPlace> getCommentPlace(@Path("id") String id);

    @POST("places/{idPlace}/comments")
    Call<StatusPostCommentPlace> postCommentPlace(@Path("idPlace") String idPlace, @Body RequestComment comment, @Header("Authorization") String authorization);
}
