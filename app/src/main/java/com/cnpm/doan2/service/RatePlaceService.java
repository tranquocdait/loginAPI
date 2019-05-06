package com.cnpm.doan2.service;

import com.cnpm.doan2.reponse.StatusRatePlace;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RatePlaceService {
    @GET("places/{id}/rates")
    Call<StatusRatePlace> getRatePlace(@Path("id") String id);
}
