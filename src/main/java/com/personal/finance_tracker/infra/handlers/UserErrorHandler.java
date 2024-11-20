package com.personal.finance_tracker.infra.handlers;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.infra.dto.LoginUserDTO;
import com.personal.finance_tracker.infra.dto.RegisterUserDTO;
import com.personal.finance_tracker.infra.entidades.UserEntity;
import com.personal.finance_tracker.infra.repositories.UserRepo;

@Component
public class UserErrorHandler {

  private final UserRepo userRepo;

  public UserErrorHandler(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  /**
   * Validates that the required fields in RegisterUserDTO are not null.
   * 
   * @param userDTO the DTO to validate
   * @throws IllegalArgumentException if any required field is null
   */
  public void validateRegisterUserDTO(RegisterUserDTO userDTO) {
    if (userDTO.getUsername() == null) {
      throw new IllegalArgumentException("Username is required");
    }
    if (userDTO.getPassword() == null) {
      throw new IllegalArgumentException("Password is required");
    }
    if (userDTO.getEmail() == null) {
      throw new IllegalArgumentException("Email is required");
    }
  }

  /**
   * Validates that the username already exists in the database.
   *
   * @param userModel the user model to validate
   * @throws IllegalArgumentException if the username already exists
   */
  public void validateUsernameAlreadyExists(UserModel userModel) {
    Optional<UserEntity> user = userRepo.findByUsername(userModel.getUsername());
    if (user.isPresent()) {
      throw new IllegalArgumentException("Username already exists");
    }
  }

  public void validateLoginUserRequest(LoginUserDTO loginUserRequest) {
    if (loginUserRequest.getUsernameOrEmail() == null) {
      throw new IllegalArgumentException("Username or email is required");
    }
    if (loginUserRequest.getPassword() == null) {
      throw new IllegalArgumentException("Password is required");
    }
  }
}
