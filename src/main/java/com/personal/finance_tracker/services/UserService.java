package com.personal.finance_tracker.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.finance_tracker.models.Expense;
import com.personal.finance_tracker.models.Income;
import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.repositories.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepo userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerUser(User user) {
    if (user.getExpense() != null) {
      List<Expense> expenses = user.getExpense(); // Ensure bi-directional relationship is set
      for (Expense expense : expenses) {
        expense.setUser(user);
      }
    }
    if (user.getIncome() != null) {
      List<Income> incomes = user.getIncome(); // Ensure bi-directional relationship is set
      for (Income income : incomes) {
        income.setUser(user);
      }
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
