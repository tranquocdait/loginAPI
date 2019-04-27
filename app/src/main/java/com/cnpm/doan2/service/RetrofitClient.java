package com.cnpm.doan2.service;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String AUTH = "Basic " + Base64.encodeToString(("").getBytes(), Base64.NO_WRAP);

    private static final String BASE_URL = "https://travel-now-app.herokuapp.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public UsersService getApi() {
        return retrofit.create(UsersService.class);
    }
}
