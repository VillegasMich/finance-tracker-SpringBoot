package com.personal.finance_tracker.services;

import java.util.Optional;
import java.util.List;

import com.personal.finance_tracker.models.User;

public interface UserServiceInterface {
  public User registerUser(User user);

  public Optional<User> findByUsername(String username);

  public Optional<User> findByCredentials(String usernameOrEmail, String password);

  public List<User> getAllUsers();

  public Optional<User> findById(Long id);

  public Optional<Double> getTotal(Long id);

  public void deleteById(Long id);
}
