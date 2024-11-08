package com.personal.finance_tracker.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping("/register/{user_id}")
  public ResponseEntity<String> registerExpense(@RequestBody Expense expense, @PathVariable Long user_id) {
    try {
      expenseService.registerExpense(expense, user_id);
      return ResponseEntity.status(HttpStatus.CREATED).body("Expense registered successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error registering expense: " + e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
    return expenseService.findById(id)
        .map(expense -> ResponseEntity.ok(expense))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/get-all")
  public ResponseEntity<Iterable<Expense>> getAllExpenses() {
    return ResponseEntity.ok(expenseService.getAllExpenses());

  }

  // TODO: Change route mapping?
  @GetMapping("/user/{id}")
  public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long id) {
    return expenseService.getExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(expenses))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
    if (expenseService.deleteExpense(id)) {
      return ResponseEntity.ok("Expense deleted successfully!");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
