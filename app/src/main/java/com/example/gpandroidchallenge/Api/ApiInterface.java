package com.example.gpandroidchallenge.Api;

import com.example.gpandroidchallenge.AddUserResponse;
import com.example.gpandroidchallenge.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/users")
    Call<UsersResponse> getUsersList(@Query("page") int page);

    @FormUrlEncoded
    @POST("api/users")
        // API's endpoints
    Call<AddUserResponse> createNewUser(@Field("name") String name,
                                       @Field("job") String job);
}
