package com.personal.finance_tracker.domain.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personal.finance_tracker.domain.models.ExpenseModel;
import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.infra.entidades.ExpenseEntity;
import com.personal.finance_tracker.infra.repositories.ExpenseRepo;
import com.personal.finance_tracker.infra.wrappers.UserWrapper;

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
    Optional<ExpenseEntity> expense = expenseRepo.findById(id);
    if (expense.isPresent()) {
      expenseRepo.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Optional<ExpenseEntity> findById(Long id) {
    return expenseRepo.findById(id);
  }

  @Override
  public Optional<List<ExpenseModel>> getExpensesByUserId(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      return Optional.ofNullable(user.get().getExpenses());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<ExpenseModel>> getNewExpensesByUserId(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      List<ExpenseModel> expenses = user.get().getExpenses().stream()
          .sorted(Comparator.comparing(ExpenseModel::getCreatedAt).reversed()).collect(Collectors.toList());
      return Optional.ofNullable(expenses);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<ExpenseModel>> getOldExpensesByUserId(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      List<ExpenseModel> expenses = user.get().getExpenses().stream()
          .sorted(Comparator.comparing(ExpenseModel::getCreatedAt)).collect(Collectors.toList());
      return Optional.ofNullable(expenses);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Double> getTotalExpense(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      double total = 0.0;
      if (user.get().getExpenses() != null) {
        List<ExpenseModel> expenses = user.get().getExpenses();
        for (ExpenseModel expense : expenses) {
          total -= expense.getAmount();
        }
      }
      return Optional.of(total);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public ExpenseEntity registerExpense(ExpenseEntity expense, Long user_id) throws IllegalArgumentException {
    Optional<UserModel> user = userService.findById(user_id);
    if (user.isPresent()) {
      expense.setUser(UserWrapper.fromModelToEntity(user.get()));
      return expenseRepo.save(expense);
    } else {
      throw new IllegalArgumentException("User not found with id " + user_id);
    }
  }

  public List<ExpenseEntity> getAllExpenses() {
    return expenseRepo.findAll();
  }
}
