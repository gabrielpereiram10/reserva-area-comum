package com.gabriel.reservaareacomum.domain.repositories;

import com.gabriel.reservaareacomum.domain.entities.User;
import com.gabriel.reservaareacomum.domain.valueObjects.CPF;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> findUserBy(CPF cpf);

}
