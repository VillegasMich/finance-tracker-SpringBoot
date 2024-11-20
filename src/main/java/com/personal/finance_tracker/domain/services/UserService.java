package com.personal.finance_tracker.domain.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.finance_tracker.domain.models.ExpenseModel;
import com.personal.finance_tracker.domain.models.IncomeModel;
import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.infra.dto.LoginUserDTO;
import com.personal.finance_tracker.infra.entidades.UserEntity;
import com.personal.finance_tracker.infra.handlers.UserErrorHandler;
import com.personal.finance_tracker.infra.repositories.UserRepo;
import com.personal.finance_tracker.infra.wrappers.UserWrapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

  private final UserRepo userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserErrorHandler userErrorHandler;

  public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder, UserErrorHandler userErrorHandler) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userErrorHandler = userErrorHandler;
  }

  public UserModel register(UserModel user) {
    userErrorHandler.validateUsernameAlreadyExists(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    UserEntity userEntity = userRepository.save(UserWrapper.fromModelToEntity(user));
    UserModel userModel = UserWrapper.fromEntityToModel(userEntity);
    return userModel;
  }

  public Optional<UserModel> login(UserModel user) {
    Optional<UserEntity> userEntity = Optional.empty();
    if (user.getUsername() != null) {
      userEntity = userRepository.findByUsername(user.getUsername());
    } else if (user.getEmail() != null) {
      userEntity = userRepository.findByEmail(user.getEmail());
    }
    if (userEntity.isPresent()) {
      if (passwordEncoder.matches(user.getPassword(), userEntity.get().getPassword())) {
        return Optional.of(UserWrapper.fromEntityToModel(userEntity.get()));
      } else {
        return Optional.empty();
      }
    } else {
      return Optional.empty();
    }
  }

  public List<UserModel> getAllUsers() {
    List<UserModel> users = userRepository.findAll().stream().map(UserWrapper::fromEntityToModel).toList();
    return users;
  }

  public Optional<UserModel> findByUsername(String username) {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      return Optional.of(UserWrapper.fromEntityToModel(user.get()));
    } else {
      return Optional.empty();
    }
  }

  public Optional<UserModel> findById(Long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      return Optional.of(UserWrapper.fromEntityToModel(user.get()));
    } else {
      return Optional.empty();
    }
  }

  public Optional<UserModel> findByEmail(String email) {
    Optional<UserEntity> user = userRepository.findByEmail(email);
    if (user.isPresent()) {
      return Optional.of(UserWrapper.fromEntityToModel(user.get()));
    } else {
      return Optional.empty();
    }
  }

  public Optional<Double> getTotal(Long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      UserModel userModel = UserWrapper.fromEntityToModel(user.get());
      double total = 0.0;
      if (userModel.getIncomes() != null) {
        List<IncomeModel> incomes = userModel.getIncomes();
        for (IncomeModel income : incomes) {
          total += income.getAmount();
        }
      }
      if (userModel.getExpenses() != null) {
        List<ExpenseModel> expenses = userModel.getExpenses();
        for (ExpenseModel expense : expenses) {
          total -= expense.getAmount();
        }
      }
      return Optional.of(total);
    } else {
      return Optional.empty();
    }

  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
