package com.example.gpandroidchallenge.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.gpandroidchallenge.UserModel;
import com.example.gpandroidchallenge.UserRepository;
import com.example.gpandroidchallenge.UsersDataSource;
import com.example.gpandroidchallenge.UsersResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class MainViewModel extends ViewModel {
    private LiveData<PagedList<UserModel>> users;
    private UserRepository userRepo;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> callFailure = new MutableLiveData<>();
    private Call<UsersResponse> retryCall;
    private Callback<UsersResponse> callback;

    private UsersDataSource usersDataSource;


    public MainViewModel(UserRepository userRepo){
        this.userRepo = userRepo;
        this.usersDataSource = new  UsersDataSource(this.userRepo);
    }

    public void loadUsers(){
//        isLoading.setValue(true);
//        callFailure.setValue(false);

//        userRepo.getListUsers(new Callback<UsersResponse>() {
//            @Override
//            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//                users.setValue(response.body().getUsersList());
//                isLoading.setValue(false);
//                callFailure.setValue(false);
//                Log.d("Response GET", "SUCCESS");
//            }
//
//            @Override
//            public void onFailure(Call<UsersResponse> call, Throwable t) {
//                // if error occurs in network transaction then we can get the error in this method.
//                Log.d("Response GET", t.toString());
//                isLoading.setValue(false);
//                callFailure.setValue(true);
//                setCallback(this);
//                retryCall = call.clone();
//            }
//        });

        DataSource.Factory factory = new DataSource.Factory() {
            @NonNull
            @Override
            public DataSource create() {
                return usersDataSource;
            }
        };

        users = new LivePagedListBuilder(factory,12).build();
    }

    public LiveData<PagedList<UserModel>> getUsers(){
        if(this.users == null){
            users = new MutableLiveData<>();
            loadUsers();
        }
        return this.users;
    }

    public LiveData<Boolean> getIsLoading(){
        isLoading.setValue(usersDataSource.getIsLoading());
        return isLoading;
    }

    public LiveData<Boolean> getCallFailure(){
        callFailure.setValue(usersDataSource.getIsLoading());
        return callFailure;
    }

    private void setCallback(Callback<UsersResponse> usersResponseCallback) {
        callback = usersResponseCallback;
    }

    public void retryCall(){
        getRetryCallback();
    }

    public void getRetryCallback(){
        //isLoading.setValue(true);
        retryCall.enqueue(callback);
    }
}
