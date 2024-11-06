
package com.personal.finance_tracker.seed;

import org.springframework.boot.CommandLineRunner;
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

  public SeedDataRunner(UserRepo userRepository, IncomeRepo incomeRepository,
      ExpenseRepo expenseRepository) {
    this.userRepo = userRepository;
    this.incomeRepo = incomeRepository;
    this.expenseRepo = expenseRepository;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    try {
      // Delete all existing records in the User, Income, and Expense tables
      expenseRepo.deleteAllInBatch();
      incomeRepo.deleteAllInBatch();
      userRepo.deleteAllInBatch();

      // Create 3 users and their related incomes and expenses
      User user1 = createUser("john_doe", "john.doe@example.com", "password123");
      // userRepo.save(user1);

      Income income1 = createIncome(3000.0, "Salary", user1);
      Income income2 = createIncome(500.0, "Freelance", user1);
      Expense expense1 = createExpense(1200.0, "Rent", user1);
      Expense expense2 = createExpense(200.0, "Utilities", user1);

      user1.setIncome(Arrays.asList(income1, income2));
      user1.setExpense(Arrays.asList(expense1, expense2));

      userRepo.save(user1); // This will cascade save the incomes and expenses

      // Repeat for other users
      User user2 = createUser("jane_smith", "jane.smith@example.com", "password456");
      // userRepo.save(user2);
      Income income3 = createIncome(4000.0, "Main Job", user2);
      Expense expense3 = createExpense(1500.0, "Mortgage", user2);
      user2.setIncome(Arrays.asList(income3));
      user2.setExpense(Arrays.asList(expense3));
      userRepo.save(user2);

      User user3 = createUser("mark_taylor", "mark.taylor@example.com", "password789");
      // userRepo.save(user3);
      Income income4 = createIncome(2500.0, "Part-Time Job", user3);
      Expense expense4 = createExpense(800.0, "Car Loan", user3);
      user3.setIncome(Arrays.asList(income4));
      user3.setExpense(Arrays.asList(expense4));
      userRepo.save(user3);

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
    user.setPassword(password);
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
