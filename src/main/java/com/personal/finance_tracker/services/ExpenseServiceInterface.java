package com.personal.finance_tracker.services;

import java.util.List;

import com.personal.finance_tracker.models.Expense;

public interface ExpenseServiceInterface {
  List<Expense> getAllExpenses();

}
