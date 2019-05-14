package com.example.gpandroidchallenge;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gpandroidchallenge.Api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    //public LiveData<List<UserModel>> getListUsers(Callback<UsersResponse> callback){
    public void getListUsers(Callback<UsersResponse> callback){
        //final MutableLiveData<List<UserModel>> users = new MutableLiveData<>();
        (Api.getClient().getUsersList(3)).enqueue(callback);
        //return users;
    }
}
