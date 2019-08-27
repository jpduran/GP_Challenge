package com.example.gpandroidchallenge;

import com.example.gpandroidchallenge.Api.Api;

import retrofit2.Callback;

public class UserRepository {

    public void getListUsers(int pageNum, Callback<UsersResponse> callback){
        (Api.getClient().getUsersList(pageNum)).enqueue(callback);
    }
}
