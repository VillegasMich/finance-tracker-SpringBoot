package com.personal.finance_tracker.domain.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.domain.models.ExpenseModel;

public interface ExpenseServiceInterface {
  List<ExpenseModel> getAllExpenses();

  Optional<List<ExpenseModel>> getExpensesByUserId(Long id);

  boolean deleteExpense(Long id);

  Optional<ExpenseModel> findById(Long id);

  ExpenseModel registerExpense(ExpenseModel expense, Long user_id);

  Optional<Double> getTotalExpense(Long id);

  Optional<List<ExpenseModel>> getNewExpensesByUserId(Long id);

  Optional<List<ExpenseModel>> getOldExpensesByUserId(Long id);

}
