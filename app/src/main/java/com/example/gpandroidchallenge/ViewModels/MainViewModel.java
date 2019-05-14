package com.example.gpandroidchallenge.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.gpandroidchallenge.UserModel;
import com.example.gpandroidchallenge.UserRepository;
import com.example.gpandroidchallenge.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<UserModel>> users;
    private UserRepository userRepo;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> callFailure = new MutableLiveData<>();
    private Call<UsersResponse> retryCall;
    private Callback<UsersResponse> callback;


    public MainViewModel(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public void loadUsers(){
        isLoading.setValue(true);
        callFailure.setValue(false);

        userRepo.getListUsers(new Callback<UsersResponse>() {
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
    }

    public LiveData<List<UserModel>> getUsers(){
        if(this.users == null){
            users = new MutableLiveData<>();
            loadUsers();
        }
        return this.users;
    }

    public LiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    public LiveData<Boolean> getCallFailure(){
        return callFailure;
    }

    private void setCallback(Callback<UsersResponse> usersResponseCallback) {
        callback = usersResponseCallback;
    }

    public void retryCall(){
        getRetryCallback();
    }

    public void getRetryCallback(){
        isLoading.setValue(true);
        retryCall.enqueue(callback);
    }
}
