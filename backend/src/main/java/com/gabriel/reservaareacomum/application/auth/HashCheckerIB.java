package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.domain.entities.Password;
import com.gabriel.reservaareacomum.domain.entities.User;

public class HashCheckerIB {
    private String value, hashedValues;

    protected HashCheckerIB(User user, Password passwordForChecking) {
        this.hashedValues = user.getPassword().getValue();
        this.value = passwordForChecking.getValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHashedValues() {
        return hashedValues;
    }

    public void setHashedValues(String hashedValues) {
        this.hashedValues = hashedValues;
    }
}
