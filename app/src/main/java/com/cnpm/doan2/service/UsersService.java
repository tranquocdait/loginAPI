package com.cnpm.doan2.service;

import com.cnpm.doan2.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsersService {
    @POST("login")
    @FormUrlEncoded
    Call<User> loginUser(@Field("userName") String userName, @Field("password") String password);
}
