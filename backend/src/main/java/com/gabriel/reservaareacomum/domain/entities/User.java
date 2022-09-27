package com.gabriel.reservaareacomum.domain.entities;

import com.gabriel.reservaareacomum.domain.valueObjects.*;

import java.util.List;

public class User {

    private ID userId;
    private String name;
    private Email email;
    private CPF cpf;
    private Password password;
    private List<Role> roles;

    public User(ID userId, String name, Email email, CPF cpf, Password password, List<Role> roles) {
        this(cpf, password);
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public User(CPF cpf, Password password) {
        this.cpf = cpf;
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public ID getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public CPF getCpf() {
        return cpf;
    }

    public Password getPassword() {
        return password;
    }
}
