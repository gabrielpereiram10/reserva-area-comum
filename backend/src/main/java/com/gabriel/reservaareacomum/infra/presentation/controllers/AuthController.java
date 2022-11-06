package com.gabriel.reservaareacomum.infra.presentation.controllers;

import com.gabriel.reservaareacomum.application.auth.AuthUseCase;
import com.gabriel.reservaareacomum.application.auth.AuthUseCaseIB;
import com.gabriel.reservaareacomum.application.auth.AuthUseCaseOB;
import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.infra.presentation.presenters.AuthPresenter;
import com.gabriel.reservaareacomum.infra.providers.JwtEncoder;
import com.gabriel.reservaareacomum.shared.exceptions.InvalidPropException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUseCase useCase;
    private final JwtEncoder encoder;

    public AuthController(AuthUseCase useCase, JwtEncoder encoder) {
        this.useCase = useCase;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody AuthUseCaseIB req) {
        try {
            AuthUseCaseOB output = useCase.execute(req);
            String token = encoder.generate(output.getUserEmail(), output.getPermissions());
            return ResponseEntity.ok(AuthPresenter.format(output, token));
        } catch (InvalidPropException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        } catch (InvalidCredentialException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }
}
