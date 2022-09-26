package com.gabriel.reservaareacomum.shared.exceptions;

public class InvalidPropException extends RuntimeException {

    private final String key;

    public InvalidPropException(String message, String key) {
        super(message);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
