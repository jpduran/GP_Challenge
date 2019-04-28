package com.example.gpandroidchallenge;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gpandroidchallenge.Api.Api;
import com.example.gpandroidchallenge.Api.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public LiveData<List<UserModel>> getListUsers(){
        final MutableLiveData<List<UserModel>> users = new MutableLiveData<>();

        (Api.getClient().getUsersList()).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                users.setValue(response.body().getUsersList());
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.d("Response GET", t.toString());
            }
        });

        return users;
    }
}
