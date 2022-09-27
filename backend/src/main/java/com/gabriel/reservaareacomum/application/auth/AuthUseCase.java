package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.application.contracts.IAuthUseCaseEncoder;
import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.domain.valueObjects.Password;
import com.gabriel.reservaareacomum.domain.entities.User;
import com.gabriel.reservaareacomum.domain.repositories.IUserRepository;
import com.gabriel.reservaareacomum.domain.valueObjects.CPF;

import java.util.Optional;

public class AuthUseCase {

    private final IUserRepository userRepository;
    private final IAuthUseCaseEncoder encoder;

    public AuthUseCase(IUserRepository userRepository, IAuthUseCaseEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    public AuthUseCaseOB execute(AuthUseCaseIB input) {
        Optional<User> userAlreadyExists = userRepository.findUserBy(new CPF(input.getCpf()));
        if (userAlreadyExists.isEmpty()) throw new InvalidCredentialException();

        User user = userAlreadyExists.get();
        boolean passwordNotMatched = !encoder.check(new AuthHashCheckerInput(user, new Password(input.getPassword())));
        if (passwordNotMatched) throw new InvalidCredentialException();

        AuthHashGeneratorOutput tokenData = encoder.generate(new AuthHashGeneratorInput(user));
        userRepository.saveRefreshToken(tokenData.getRefreshToken());
        return new AuthUseCaseOB(user, tokenData);
    }
}
