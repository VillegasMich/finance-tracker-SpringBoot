package com.personal.finance_tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.finance_tracker.models.Expense;
import com.personal.finance_tracker.repositories.ExpenseRepo;

@Service
public class ExpenseService {

  private final ExpenseRepo expenseRepo;

  public ExpenseService(ExpenseRepo expenseRepo) {
    this.expenseRepo = expenseRepo;
  }

  public List<Expense> getAllExpenses() {
    return expenseRepo.findAll();
  }
}
