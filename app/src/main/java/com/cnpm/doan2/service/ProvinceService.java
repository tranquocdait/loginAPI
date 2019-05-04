package com.cnpm.doan2.service;

import com.cnpm.doan2.models.RequestProvince;
import com.cnpm.doan2.reponse.StatusDelete;
import com.cnpm.doan2.reponse.StatusProvince;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ProvinceService {
    @GET("provinces")
    Call<StatusProvince> getListProvince();

    @POST("provinces")
    Call<StatusProvince> postProvince();

    @PUT("categories/{id}")
    Call<StatusProvince> putProvince(@Part("id") Integer id, @Body RequestProvince province);

    @DELETE("categories/{id}")
    Call<StatusDelete> deleteProvince(@Part("id") Integer id);
}
