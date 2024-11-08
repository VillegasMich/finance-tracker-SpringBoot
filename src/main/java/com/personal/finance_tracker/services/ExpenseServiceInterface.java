package com.personal.finance_tracker.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.models.Expense;

public interface ExpenseServiceInterface {
  List<Expense> getAllExpenses();

  Optional<List<Expense>> getExpensesByUserId(Long id);

  boolean deleteExpense(Long id);

  Optional<Expense> findById(Long id);

  Expense registerExpense(Expense expense, Long user_id);

}
