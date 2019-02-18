package com.work.matchmaking.service.repository;

import com.work.matchmaking.service.model.Response1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    String HTTPS_API_USER_URL = "https://randomuser.me/";

    @GET("api/")
    Call<Response1> queryUser(@Query("results") int resultNo);
}
