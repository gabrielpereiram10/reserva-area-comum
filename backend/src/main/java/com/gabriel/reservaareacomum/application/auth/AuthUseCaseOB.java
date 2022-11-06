package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.domain.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthUseCaseOB {

    private Long userId;
    private String userName, userEmail;
    private List<String> roles;
    private Map<String, Boolean> permissions = new HashMap<>();

    protected AuthUseCaseOB(User user) {
        this.userId = user.getUserId();
        this.userName = user.getName();
        this.userEmail = user.getEmail().getValue();
        this.roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toList());
    }

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
