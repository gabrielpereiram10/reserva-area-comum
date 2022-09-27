package com.gabriel.reservaareacomum.application.contracts;

public interface HashGenerator<I, O> {

    O generate(I input);
}
