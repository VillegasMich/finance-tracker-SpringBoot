package com.personal.finance_tracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.finance_tracker.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByUsernameOrEmail(String usernameOrEmail, String email);
}
