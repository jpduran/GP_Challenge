package com.example.gpandroidchallenge;

import androidx.paging.DataSource;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UsersResponse {
    @SerializedName("page")
    private int pageNum;
    @SerializedName("per_page")
    private int usersPerPage;
    @SerializedName("total")
    private int totalUsers;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("data")
    private List<UserModel> usersList;

    public UsersResponse(int pageNum, int usersPerPage, int totalUsers, int totalPages, List<UserModel> usersList) {
        this.pageNum = pageNum;
        this.usersPerPage = usersPerPage;
        this.totalUsers = totalUsers;
        this.totalPages = totalPages;
        this.usersList = usersList;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getUsersPerPage() {
        return usersPerPage;
    }

    public void setUsersPerPage(int usersPerPage) {
        this.usersPerPage = usersPerPage;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<UserModel> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserModel> usersList) {
        this.usersList = usersList;
    }
}
