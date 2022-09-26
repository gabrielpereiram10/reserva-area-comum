package com.gabriel.reservaareacomum.application.auth;

import com.gabriel.reservaareacomum.application.contracts.Encoder;
import com.gabriel.reservaareacomum.application.exceptions.InvalidCredentialException;
import com.gabriel.reservaareacomum.domain.entities.Password;
import com.gabriel.reservaareacomum.domain.entities.User;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AuthUseCaseTest {

    private AuthUseCase sut;
    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private Encoder encoder;
    private String cpf;

    @BeforeEach
    void setUp() {
        cpf = "08753533631";
        sut = new AuthUseCase(userRepository, encoder);
    }

    @Test
    @DisplayName("Não deve permitir autenticação com CPF não cadastrado")
    void testeLogin() {
        AuthUseCaseIB input = new AuthUseCaseIB(cpf, "password");
        when(userRepository.findUserBy(new CPF(cpf))).thenReturn(Optional.empty());
        assertThrows(InvalidCredentialException.class, () -> sut.execute(input));
    }

    @Test
    @DisplayName("Não deve permitir autenticação com senha diferente da cadastrada")
    void testeSenha() {
        AuthUseCaseIB input = new AuthUseCaseIB(cpf, "111sss@@");
        User expected = new User(new CPF(cpf), new Password("senhaCadastrada"));
        when(userRepository.findUserBy(expected.getCpf())).thenReturn(Optional.of(expected));
        when(encoder.check(any())).thenReturn(false);
        assertThrows(InvalidCredentialException.class, () -> sut.execute(input));
    }
}
