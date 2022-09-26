package com.gabriel.reservaareacomum.domain.exceptions;

import com.gabriel.reservaareacomum.shared.exceptions.InvalidPropException;

public class InvalidCPFException extends InvalidPropException {

    public InvalidCPFException() {
        super("CPF inválido.", "cpf");
    }
}
