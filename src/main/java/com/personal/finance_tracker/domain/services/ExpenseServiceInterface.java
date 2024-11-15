package com.personal.finance_tracker.domain.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.domain.models.ExpenseModel;
import com.personal.finance_tracker.infra.entidades.ExpenseEntity;

public interface ExpenseServiceInterface {
  List<ExpenseEntity> getAllExpenses();

  Optional<List<ExpenseModel>> getExpensesByUserId(Long id);

  boolean deleteExpense(Long id);

  Optional<ExpenseEntity> findById(Long id);

  ExpenseEntity registerExpense(ExpenseEntity expense, Long user_id);

  Optional<Double> getTotalExpense(Long id);

  Optional<List<ExpenseModel>> getNewExpensesByUserId(Long id);

  Optional<List<ExpenseModel>> getOldExpensesByUserId(Long id);

}
