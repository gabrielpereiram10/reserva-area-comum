package com.gabriel.reservaareacomum.domain.entities;

import com.gabriel.reservaareacomum.domain.valueObjects.CPF;

public class User {

    private CPF cpf;
    private Password password;

    public User(CPF cpf, Password password) {
        this.cpf = cpf;
        this.password = password;
    }

    public CPF getCpf() {
        return cpf;
    }

    public Password getPassword() {
        return password;
    }
}
