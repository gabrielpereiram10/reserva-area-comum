package com.gabriel.reservaareacomum.domain.valueObjects;

import java.util.Objects;
import java.util.UUID;

public class Token {

    private final UUID value;

    public Token(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
