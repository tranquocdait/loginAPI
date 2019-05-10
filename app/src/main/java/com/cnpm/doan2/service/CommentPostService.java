package com.cnpm.doan2.service;

import com.cnpm.doan2.models.RequestComment;
import com.cnpm.doan2.reponse.StatusCommentPlace;
import com.cnpm.doan2.reponse.StatusPostCommentPlace;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentPostService {
    @GET("posts/{postId}/comments")
    Call<StatusCommentPlace> getCommentPost(@Path("postId") String postId);

    @POST("posts/{postId}/comments")
    Call<StatusPostCommentPlace> postCommentPost(@Path("postId") String idPlace, @Body RequestComment comment, @Header("Authorization") String authorization);
}
