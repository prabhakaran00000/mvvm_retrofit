package com.csquare.sampleapp.data.remote;

import com.csquare.sampleapp.model.LoginUser;
import com.csquare.sampleapp.model.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    String BASE_URL = "https://reqres.in/";

    @POST("/api/login")
    Call<LoginUser> getLogin(@Body LoginUser loginUser);

    @GET("/api/users")
    Call<UserList> getUsers(@Query("page") int pageSize);
}
