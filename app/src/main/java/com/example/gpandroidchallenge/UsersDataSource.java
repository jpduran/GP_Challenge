package com.example.gpandroidchallenge;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersDataSource extends PageKeyedDataSource<Integer, UserModel> {
    private UserRepository userRepo;
    private boolean isLoading;
    private boolean callFailure;

    public UsersDataSource(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, UserModel> callback) {
        isLoading = true;
        final int page = 1;

        Callback<UsersResponse> requestCallback = new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                UsersResponse userResponse = response.body();

                if(userResponse == null){
                    return;
                }

                callback.onResult(userResponse.getUsersList(), 0, page +1);
                isLoading = false;
                callFailure = false;
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.d("Response GET", t.toString());
                isLoading = false;
                callFailure = true;
//                callFailure.setValue(true);
//                setCallback(this);
//                retryCall = call.clone();
            }
        };


        userRepo.getListUsers(page,requestCallback);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, UserModel> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, UserModel> callback) {
        final int page = params.key;

        Callback<UsersResponse> requestCallback = new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                UsersResponse userResponse = response.body();

                if(userResponse == null){
                    return;
                }

                callback.onResult(userResponse.getUsersList(), page +1);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.d("Response GET", t.toString());
//                isLoading.setValue(false);
//                callFailure.setValue(true);
//                setCallback(this);
//                retryCall = call.clone();
            }
        };

        userRepo.getListUsers(page,requestCallback);
    }

    public boolean getIsLoading(){
        return isLoading;
    }
}
