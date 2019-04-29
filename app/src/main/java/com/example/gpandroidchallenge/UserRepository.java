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
    private Call<UsersResponse> retryCall;
    private Callback<UsersResponse> callback;

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> callFailure = new MutableLiveData<>();

    public LiveData<List<UserModel>> getListUsers(){
        final MutableLiveData<List<UserModel>> users = new MutableLiveData<>();
        isLoading.setValue(true);
        callFailure.setValue(false);

        (Api.getClient().getUsersList()).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                users.setValue(response.body().getUsersList());
                isLoading.setValue(false);
                callFailure.setValue(false);
                Log.d("Response GET", "SUCCESS");
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.d("Response GET", t.toString());
                isLoading.setValue(false);
                callFailure.setValue(true);
                setCallback(this);
                retryCall = call.clone();
            }
        });

        return users;
    }

    private void setCallback(Callback<UsersResponse> usersResponseCallback) {
        callback = usersResponseCallback;
    }

    public LiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    public LiveData<Boolean> getCallFailure(){
        return callFailure;
    }

    public void getRetryCallback(){
        isLoading.setValue(true);
        retryCall.enqueue(callback);
    }
}
