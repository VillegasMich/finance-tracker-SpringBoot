
package com.personal.finance_tracker.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.personal.finance_tracker.models.Expense;
import com.personal.finance_tracker.models.Income;
import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.repositories.ExpenseRepo;
import com.personal.finance_tracker.repositories.IncomeRepo;
import com.personal.finance_tracker.repositories.UserRepo;

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
        User user = createUser(username, email, password);
        Income income = createIncome(2500.0, "Part-Time Job", user);
        Expense expense = createExpense(1500.0, "Mortgage", user);
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
  private User createUser(String username, String email, String password) {
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    return user;
  }

  private Income createIncome(Double amount, String description, User user) {
    Income income = new Income();
    income.setAmount(amount);
    income.setDescription(description);
    income.setUser(user);
    return income;
  }

  private Expense createExpense(Double amount, String description, User user) {
    Expense expense = new Expense();
    expense.setAmount(amount);
    expense.setDescription(description);
    expense.setUser(user);
    return expense;
  }
}
