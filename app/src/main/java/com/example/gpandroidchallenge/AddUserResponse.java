package com.example.gpandroidchallenge;

import com.google.gson.annotations.SerializedName;

public class AddUserResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String jobTitle;
    @SerializedName("createdAt")
    private String createdDateTime;

    public AddUserResponse(int id, String name, String jobTitle, String createdDateTime) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.createdDateTime = createdDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
