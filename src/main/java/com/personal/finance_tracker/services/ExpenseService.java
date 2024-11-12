package com.personal.finance_tracker.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personal.finance_tracker.models.Expense;
import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.repositories.ExpenseRepo;

@Service
public class ExpenseService implements ExpenseServiceInterface {

  private final ExpenseRepo expenseRepo;
  private final UserService userService;

  public ExpenseService(ExpenseRepo expenseRepo, UserService userService) {
    this.expenseRepo = expenseRepo;
    this.userService = userService;
  }

  @Override
  public boolean deleteExpense(Long id) {
    Optional<Expense> expense = expenseRepo.findById(id);
    if (expense.isPresent()) {
      expenseRepo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Optional<Expense> findById(Long id) {
    return expenseRepo.findById(id);
  }

  @Override
  public Optional<List<Expense>> getExpensesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      return Optional.ofNullable(user.get().getExpense());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<Expense>> getNewExpensesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      List<Expense> expenses = user.get().getExpense().stream()
          .sorted(Comparator.comparing(Expense::getCreatedAt).reversed()).collect(Collectors.toList());
      return Optional.ofNullable(expenses);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<Expense>> getOldExpensesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      List<Expense> expenses = user.get().getExpense().stream()
          .sorted(Comparator.comparing(Expense::getCreatedAt)).collect(Collectors.toList());
      return Optional.ofNullable(expenses);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Double> getTotalExpense(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      double total = 0.0;
      if (user.get().getExpense() != null) {
        List<Expense> expenses = user.get().getExpense();
        for (Expense expense : expenses) {
          total -= expense.getAmount();
        }
      }
      return Optional.of(total);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Expense registerExpense(Expense expense, Long user_id) throws IllegalArgumentException {
    Optional<User> user = userService.findById(user_id);
    if (user.isPresent()) {
      expense.setUser(user.get());
      return expenseRepo.save(expense);
    } else {
      throw new IllegalArgumentException("User not found with id " + user_id);
    }
  }

  public List<Expense> getAllExpenses() {
    return expenseRepo.findAll();
  }
}
