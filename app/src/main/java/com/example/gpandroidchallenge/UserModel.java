package com.example.gpandroidchallenge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String avatarURL;

    public UserModel(int id, String firstName, String lastName, String avatarURL) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarURL = avatarURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj != null && obj.getClass() == UserModel.class){
            UserModel userModel = (UserModel) obj;
            return this.firstName.equals(userModel.getFirstName()) &&
                    this.lastName.equals(userModel.getLastName()) &&
                    this.avatarURL.equals(userModel.getAvatarURL());
        } else {
            return false;
        }
    }

    public static final DiffUtil.ItemCallback<UserModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserModel>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull UserModel oldUser, @NonNull UserModel newUser) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldUser.getId() == newUser.getId();
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull UserModel oldUser, @NonNull UserModel newUser) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldUser.equals(newUser);
                }
            };
}
