package com.gabriel.reservaareacomum.infra.persistence.repositories;

import com.gabriel.reservaareacomum.domain.entities.User;
import com.gabriel.reservaareacomum.domain.repositories.IUserRepository;
import com.gabriel.reservaareacomum.domain.valueObjects.*;
import com.gabriel.reservaareacomum.infra.persistence.models.RoleModel;
import com.gabriel.reservaareacomum.infra.persistence.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class UserRepository implements IUserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findUserBy(CPF cpf) {
        Optional<UserModel> result = userJpaRepository.findByCpf(cpf.getValue());
        if (result.isEmpty()) return Optional.empty();

        UserModel user = result.get();
        System.out.println(user.getRoles());
        return Optional.of(new User(
                user.getUserId(),
                user.getName(),
                new Email(user.getEmail()),
                new CPF(user.getCpf()),
                new Password(user.getPassword()),
                user.getRoles().stream().map(RoleModel::getRoleName).collect(Collectors.toList())
        ));
    }
}
