package com.gabriel.reservaareacomum.infra.providers;

import com.gabriel.reservaareacomum.application.auth.AuthHashCheckerInput;
import com.gabriel.reservaareacomum.application.contracts.HashChecker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements HashChecker<AuthHashCheckerInput> {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean check(AuthHashCheckerInput input) {
        return encoder.matches(input.getValue(), input.getHashedValues());
    }
}
