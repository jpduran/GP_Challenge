package com.example.gpandroidchallenge.Api;

import com.example.gpandroidchallenge.AddUserResponse;
import com.example.gpandroidchallenge.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("api/users")
    Call<UsersResponse> getUsersList();

    @FormUrlEncoded
    @POST("api/users")
        // API's endpoints
    Call<AddUserResponse> createNewUser(@Field("name") String name,
                                       @Field("job") String job);
}
