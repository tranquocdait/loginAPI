package com.cnpm.doan2.service;

import com.cnpm.doan2.models.RequestChangeAvatar;
import com.cnpm.doan2.models.RequestTourist;
import com.cnpm.doan2.reponse.StatusDelete;
import com.cnpm.doan2.reponse.StatusTourist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TouristService {
    @GET("tourists")
    Call<StatusTourist> getListTourist();

    @GET("tourists/{id}")
    Call<StatusTourist> getTourist(@Path("id") String id);

    @POST("tourists")
    Call<StatusTourist> postTourist();

    @PUT("tourists/{id}")
    Call<StatusTourist> putTourist(@Part("id") Integer id, @Body RequestTourist tourist);

    @PUT("tourists/{id}")
    Call<StatusTourist> putTourist(@Part("id") Integer id, @Body RequestChangeAvatar avatar);

    @DELETE("categories/{id}")
    Call<StatusDelete> deleteTourist(@Part("id") Integer id);
}
