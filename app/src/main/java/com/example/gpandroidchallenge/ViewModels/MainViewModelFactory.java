package com.example.gpandroidchallenge.ViewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gpandroidchallenge.UserRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final UserRepository userRepo;

    public MainViewModelFactory(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(userRepo);
    }
}
