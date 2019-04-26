package com.example.gpandroidchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gpandroidchallenge.Api.Api;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserModel> userEntries;
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

        getUserListData();
    }

    private void getUserListData() {

        tryAgain.setVisibility(View.GONE);
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        (Api.getClient().getUsersList()).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                progressDialog.dismiss();
                userEntries = response.body().getUsersList();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.d("responseGET", t.toString());
                tryAgain.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this);
        usersAdapter.submitList(userEntries);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }

}
