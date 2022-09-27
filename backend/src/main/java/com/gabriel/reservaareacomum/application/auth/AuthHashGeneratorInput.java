package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.domain.entities.User;

public class AuthHashGeneratorInput {
    private String userName, userEmail;

    public AuthHashGeneratorInput(User user) {
        this.userName = user.getName();
        this.userEmail = user.getEmail().getValue();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
