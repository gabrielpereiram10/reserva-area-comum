package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.domain.entities.User;
import com.gabriel.reservaareacomum.domain.repositories.IUserRepository;
import com.gabriel.reservaareacomum.domain.valueObjects.CPF;

import java.util.Optional;

public class AuthUseCase {

    private final IUserRepository userRepository;

    public AuthUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void execute(AuthUseCaseIB input) {
        Optional<User> userAlreadyExists = userRepository.findUserBy(new CPF(input.getCpf()));
        if (userAlreadyExists.isEmpty()) throw new InvalidCredentialException();
    }
}
