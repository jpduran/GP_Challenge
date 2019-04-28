package com.example.gpandroidchallenge;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserListData();
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createUserIntent = new Intent(MainActivity.this, CreateUser.class);
                startActivity(createUserIntent);
            }
        });

        //getUserListData();
        setDataInRecyclerView();
    }

    private void getUserListData() {

        tryAgain.setVisibility(View.GONE);
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

//        (Api.getClient().getUsersList()).enqueue(new Callback<UsersResponse>() {
//            @Override
//            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//                progressDialog.dismiss();
//                userEntries = response.body().getUsersList();
//                setDataInRecyclerView();
//            }
//
//            @Override
//            public void onFailure(Call<UsersResponse> call, Throwable t) {
//                // if error occurs in network transaction then we can get the error in this method.
//                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
//                Log.d("responseGET", t.toString());
//                tryAgain.setVisibility(View.VISIBLE);
//                progressDialog.dismiss();
//            }
//        });
    }

    private void setDataInRecyclerView() {
        UserRepository userRepository = new UserRepository();
        MainViewModelFactory factory =new MainViewModelFactory(userRepository);
        MainViewModel mainViewModel = ViewModelProviders.of(this, factory)
                                        .get(MainViewModel.class);

        final UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this);

        mainViewModel.getUsers().observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                usersAdapter.submitList(userModels);
            }
        });

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(usersAdapter);
    }

}
