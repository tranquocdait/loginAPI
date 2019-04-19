package com.cnpm.doan2.service;

import com.cnpm.doan2.models.Tourist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface TouristService {
    @POST("login")
    Call<Tourist> loginTourist(
            @Field("userName") String userName,
            @Field("password") String password
    );
}
