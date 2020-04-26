package com.csquare.sampleapp.utils;


import com.csquare.sampleapp.data.remote.APIService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkUtils {

    public static APIService getAPIService() {
        Retrofit.Builder retrofit = new Retrofit.Builder();

        retrofit.baseUrl(APIService.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.build().create(APIService.class);
    }
}
