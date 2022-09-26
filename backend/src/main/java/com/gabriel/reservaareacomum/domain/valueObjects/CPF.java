package com.gabriel.reservaareacomum.domain.valueObjects;

import com.gabriel.reservaareacomum.domain.exceptions.InvalidCPFException;

public class CPF {

    private final String value;
    public CPF(String value) {
        if (!value.matches("^\\d{11}$")) throw new InvalidCPFException();

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
