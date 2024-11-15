
package com.personal.finance_tracker.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.personal.finance_tracker.infra.entidades.ExpenseEntity;
import com.personal.finance_tracker.infra.entidades.IncomeEntity;
import com.personal.finance_tracker.infra.entidades.UserEntity;
import com.personal.finance_tracker.infra.repositories.ExpenseRepo;
import com.personal.finance_tracker.infra.repositories.IncomeRepo;
import com.personal.finance_tracker.infra.repositories.UserRepo;

import java.util.Arrays;

@Component
public class SeedDataRunner implements CommandLineRunner {

  private final UserRepo userRepo;
  private final IncomeRepo incomeRepo;
  private final ExpenseRepo expenseRepo;
  private final PasswordEncoder passwordEncoder;

  public SeedDataRunner(UserRepo userRepository, IncomeRepo incomeRepository,
      ExpenseRepo expenseRepository, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepository;
    this.incomeRepo = incomeRepository;
    this.expenseRepo = expenseRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    try {
      // Delete all existing records in the User, Income, and Expense tables
      expenseRepo.deleteAllInBatch();
      incomeRepo.deleteAllInBatch();
      userRepo.deleteAllInBatch();

      // Create seed data for User, Income, and Expense
      for (int i = 0; i < 10; i++) {
        String username = "user" + i;
        String email = username + "@example.com";
        String password = "password";
        UserEntity user = createUser(username, email, password);
        IncomeEntity income = createIncome(2500.0, "Part-Time Job " + username, user);
        ExpenseEntity expense = createExpense(1500.0, "Mortgage " + username, user);
        user.setIncome(Arrays.asList(income));
        user.setExpense(Arrays.asList(expense));
        userRepo.save(user);
      }

      System.out.println("Seed data initialized.");
    } catch (Exception e) {
      System.out.println("Error initializing seed data: " + e.getMessage());
      e.printStackTrace(); // Debugging the error
    }
  }

  // Helper methods to create user, income, and expense
  private UserEntity createUser(String username, String email, String password) {
    UserEntity user = new UserEntity();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    return user;
  }

  private IncomeEntity createIncome(Double amount, String description, UserEntity user) {
    IncomeEntity income = new IncomeEntity();
    income.setAmount(amount);
    income.setDescription(description);
    income.setUser(user);
    return income;
  }

  private ExpenseEntity createExpense(Double amount, String description, UserEntity user) {
    ExpenseEntity expense = new ExpenseEntity();
    expense.setAmount(amount);
    expense.setDescription(description);
    expense.setUser(user);
    return expense;
  }
}
