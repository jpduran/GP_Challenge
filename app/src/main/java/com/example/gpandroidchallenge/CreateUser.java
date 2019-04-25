package com.example.gpandroidchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gpandroidchallenge.Api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUser extends AppCompatActivity {

    EditText nameEditText;
    EditText lastNameEditText;
    Button addButton;

    AddUserResponse addUserResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        nameEditText = findViewById(R.id.editTextName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser() {

        if(nameEditText.getText().toString().trim().isEmpty() ||
            lastNameEditText.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter a name and a job title.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(CreateUser.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        (Api.getClient().createNewUser(nameEditText.getText().toString().trim(),
                lastNameEditText.getText().toString().trim())).enqueue(new Callback<AddUserResponse>() {
            @Override
            public void onResponse(Call<AddUserResponse> call, Response<AddUserResponse> response) {
                addUserResponse = response.body();
                Toast.makeText(getApplicationContext(), "New user added with ID: " + response.body().getId(),
                        Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<AddUserResponse> call, Throwable t) {
                Log.d("responsePOST", t.getStackTrace().toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                addButton.setText(R.string.try_again);
                progressDialog.dismiss();
            }
        });
    }
}
