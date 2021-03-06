package com.example.gpandroidchallenge.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gpandroidchallenge.UserModel;
import com.example.gpandroidchallenge.UserRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<UserModel>> users;
    private UserRepository userRepo;

    public MainViewModel(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public void init(){
        if(this.users != null){
            return;
        }
        users = userRepo.getListUsers();
    }

    public LiveData<List<UserModel>> getUsers(){
        return this.users;
    }

    public LiveData<Boolean> getIsLoading(){
        return userRepo.getIsLoading();
    }

    public LiveData<Boolean> getCallFailure(){
        return userRepo.getCallFailure();
    }

    public void retryCall(){
        userRepo.getRetryCallback();
    }
}
