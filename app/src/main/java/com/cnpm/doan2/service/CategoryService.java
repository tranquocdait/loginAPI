package com.cnpm.doan2.service;

import com.cnpm.doan2.models.RequestCategory;
import com.cnpm.doan2.reponse.StatusCategory;
import com.cnpm.doan2.reponse.StatusDelete;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface CategoryService {
    @GET("categories")
    Call<StatusCategory> getListCategory();

    @POST("categories")
    Call<StatusCategory> postCategory(@Body RequestCategory category);

    @PUT("categories/{id}")
    Call<StatusCategory> putCategory(@Part("id") Integer id, @Body RequestCategory category);

    @DELETE("categories/{id}")
    Call<StatusDelete> deleteCategory(@Part("id") Integer id);
}
