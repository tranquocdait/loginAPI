package com.cnpm.doan2.service;

import com.cnpm.doan2.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceService {
    @GET("places")
    Call<List<Place>> getListPlace();
}
