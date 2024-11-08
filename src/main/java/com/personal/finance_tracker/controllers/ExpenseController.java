package com.personal.finance_tracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.finance_tracker.models.Expense;
import com.personal.finance_tracker.services.ExpenseServiceInterface;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

  private final ExpenseServiceInterface expenseService;

  public ExpenseController(ExpenseServiceInterface expenseService) {
    this.expenseService = expenseService;
  }

  @GetMapping("/get-all")
  public ResponseEntity<Iterable<Expense>> getAllExpenses() {
    return ResponseEntity.ok(expenseService.getAllExpenses());
  }

}
