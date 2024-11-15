package com.personal.finance_tracker.domain.services;

import java.util.Optional;

import com.personal.finance_tracker.domain.models.UserModel;

import java.util.List;

public interface UserServiceInterface {
  public UserModel registerUser(UserModel user);

  public List<UserModel> getAllUsers();

  public Optional<UserModel> findByUsername(String username);

  public Optional<UserModel> findById(Long id);

  public Optional<UserModel> findByEmail(String email);

  public Optional<Double> getTotal(Long id);

  public void deleteById(Long id);
}
