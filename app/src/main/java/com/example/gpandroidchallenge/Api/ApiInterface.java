package com.example.gpandroidchallenge.Api;

import com.example.gpandroidchallenge.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/users")
    Call<UsersResponse> getUsersList();
}
