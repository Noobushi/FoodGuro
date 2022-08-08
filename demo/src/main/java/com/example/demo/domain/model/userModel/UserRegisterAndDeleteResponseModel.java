package com.example.demo.domain.model.userModel;

public class UserRegisterAndDeleteResponseModel {

    private String username;

    private String userRole;

    public UserRegisterAndDeleteResponseModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
