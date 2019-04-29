package com.example.gpandroidchallenge;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpandroidchallenge.ViewModels.MainViewModel;
import com.example.gpandroidchallenge.ViewModels.MainViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button tryAgain;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        tryAgain = findViewById(R.id.tryAgainButton);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createUserIntent = new Intent(MainActivity.this, CreateUser.class);
                startActivity(createUserIntent);
            }
        });

        setDataInRecyclerView();
    }

    private void setDataInRecyclerView() {
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait");

        UserRepository userRepository = new UserRepository();
        MainViewModelFactory factory =new MainViewModelFactory(userRepository);
        final MainViewModel mainViewModel = ViewModelProviders.of(this, factory)
                                        .get(MainViewModel.class);
        mainViewModel.init();

        final UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this);

        mainViewModel.getUsers().observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                usersAdapter.submitList(userModels);
            }
        });

        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    pd.show();
                }else{
                    pd.dismiss();
                }
            }
        });

        mainViewModel.getCallFailure().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    tryAgain.setVisibility(View.VISIBLE);
                } else {
                    tryAgain.setVisibility(View.GONE);
                }
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.retryCall();
            }
        });

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(usersAdapter);
    }
}
