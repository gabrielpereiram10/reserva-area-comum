package com.gabriel.reservaareacomum.application.exceptions;

public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException() {
        super("Credenciais inválidas.");
    }
}
