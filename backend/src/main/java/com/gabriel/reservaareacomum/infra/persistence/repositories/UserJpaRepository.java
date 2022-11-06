package com.gabriel.reservaareacomum.infra.persistence.repositories;

import com.gabriel.reservaareacomum.infra.persistence.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByCpf(String cpf);
}
