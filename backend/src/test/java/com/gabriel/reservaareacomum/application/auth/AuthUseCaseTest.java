package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.domain.repositories.IUserRepository;
import com.gabriel.reservaareacomum.domain.valueObjects.CPF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AuthUseCaseTest {

    private AuthUseCase sut;
    @MockBean
    private IUserRepository userRepository;
    private String cpf;

    @BeforeEach
    void setUp() {
        cpf = "08753533631";
        sut = new AuthUseCase(userRepository);
    }

    @Test
    @DisplayName("Não deve permitir autenticação com CPF não cadastrado")
    void testeLogin() {
        AuthUseCaseIB input = new AuthUseCaseIB(cpf, "password");
        when(userRepository.findUserBy(new CPF(cpf))).thenReturn(Optional.empty());
        assertThrows(InvalidCredentialException.class, () -> sut.execute(input));
    }
}
