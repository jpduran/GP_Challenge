package com.example.gpandroidchallenge;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

public class UsersDataSource extends PageKeyedDataSource<Integer, UserModel> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, UserModel> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, UserModel> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, UserModel> callback) {

    }
}
