package com.gabriel.reservaareacomum.domain.valueObjects;

import java.util.Objects;
import java.util.UUID;

public class ID {

    private final UUID value;

    public ID(UUID value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public UUID getValue() {
        return value;
    }
}
