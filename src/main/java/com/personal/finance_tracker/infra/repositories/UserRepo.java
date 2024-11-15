package com.personal.finance_tracker.infra.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.finance_tracker.infra.entidades.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByEmail(String email);

}
