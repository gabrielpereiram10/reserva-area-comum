package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.application.contracts.Encoder;
import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.domain.entities.Password;
import com.gabriel.reservaareacomum.domain.entities.User;
import com.gabriel.reservaareacomum.domain.repositories.IUserRepository;
import com.gabriel.reservaareacomum.domain.valueObjects.CPF;

import java.util.Optional;

public class AuthUseCase {

    private final IUserRepository userRepository;
    private final Encoder encoder;

    public AuthUseCase(IUserRepository userRepository, Encoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    public void execute(AuthUseCaseIB input) {
        Optional<User> userAlreadyExists = userRepository.findUserBy(new CPF(input.getCpf()));
        if (userAlreadyExists.isEmpty()) throw new InvalidCredentialException();

        User user = userAlreadyExists.get();
        boolean passwordNotMatched = !encoder.check(new HashCheckerIB(user, new Password(input.getPassword())));
        if (passwordNotMatched) throw new InvalidCredentialException();
    }
}
