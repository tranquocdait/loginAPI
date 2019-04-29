package com.cnpm.doan2.service;

import com.cnpm.doan2.models.Province;
import com.cnpm.doan2.reponse.StatusProvince;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProvinceService {
    @GET("provinces")
    Call<StatusProvince> getListProvince();
}
