package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.domain.entities.User;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthUseCaseOB {

    private UUID userId, accessToken, refreshToken;
    private String userName, userEmail, tokenType;
    private List<String> roles;

    protected AuthUseCaseOB(User user, AuthHashGeneratorOutput tokenData) {
        this.userId = user.getUserId().getValue();
        this.userName = user.getName();
        this.userEmail = user.getEmail().getValue();
        this.roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toList());
        this.tokenType = tokenData.getTokenType();
        this.accessToken = tokenData.getAccessToken().getValue();
        this.refreshToken = tokenData.getRefreshToken().getValue();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(UUID accessToken) {
        this.accessToken = accessToken;
    }

    public UUID getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(UUID refreshToken) {
        this.refreshToken = refreshToken;
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

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
