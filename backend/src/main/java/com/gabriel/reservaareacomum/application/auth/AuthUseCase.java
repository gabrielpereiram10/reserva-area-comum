package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.application.contracts.HashChecker;
import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.domain.valueObjects.Password;
import com.gabriel.reservaareacomum.domain.entities.User;
import com.gabriel.reservaareacomum.domain.repositories.IUserRepository;
import com.gabriel.reservaareacomum.domain.valueObjects.CPF;
import com.gabriel.reservaareacomum.shared.valueObjects.Role;

import java.util.Optional;

public class AuthUseCase {

    private final IUserRepository userRepository;
    private final HashChecker<AuthHashCheckerInput> hashChecker;

    public AuthUseCase(IUserRepository userRepository, HashChecker<AuthHashCheckerInput> hashChecker) {
        this.userRepository = userRepository;
        this.hashChecker = hashChecker;
    }

    public AuthUseCaseOB execute(AuthUseCaseIB input) {
        User userSignIn = new User(new CPF(input.getCpf()), new Password(input.getPassword()));
        Optional<User> userAlreadyExists = userRepository.findUserBy(userSignIn.getCpf());
        if (userAlreadyExists.isEmpty()) throw new InvalidCredentialException();

        User user = userAlreadyExists.get();
        boolean passwordNotMatched = !hashChecker.check(new AuthHashCheckerInput(user, userSignIn));
        if (passwordNotMatched) throw new InvalidCredentialException();

        AuthUseCaseOB output = new AuthUseCaseOB(user);
        definePermissions(user, output);
        return output;
    }

    private void definePermissions(User user, AuthUseCaseOB output) {
        for (Role role : user.getRoles()) {
            Boolean isAdmin = role == Role.ADMIN;
            output.getPermissions().put("isAdmin", isAdmin);
            Boolean isMorador = role == Role.MORADOR;
            output.getPermissions().put("isMorador", isMorador);
        }
    }
}
