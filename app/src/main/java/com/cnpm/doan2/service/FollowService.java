package com.cnpm.doan2.service;

import com.cnpm.doan2.reponse.StatusCategory;
import com.cnpm.doan2.reponse.StatusFollow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FollowService {
    @GET("tourists/{touristId}/follows")
    Call<StatusFollow> getFollow(@Path("touristId") String touristId);
}
