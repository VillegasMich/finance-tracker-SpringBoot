package com.personal.finance_tracker.infra.controllers;

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

import com.personal.finance_tracker.domain.services.ExpenseServiceInterface;
import com.personal.finance_tracker.infra.dto.ResponseExpenseDTO;
import com.personal.finance_tracker.infra.entidades.ExpenseEntity;
import com.personal.finance_tracker.infra.wrappers.ExpenseWrapper;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

  private final ExpenseServiceInterface expenseService;

  public ExpenseController(ExpenseServiceInterface expenseService) {
    this.expenseService = expenseService;
  }

  @PostMapping("/register/{user_id}")
  public ResponseEntity<String> registerExpense(@RequestBody ExpenseEntity expense, @PathVariable Long user_id) {
    try {
      expenseService.registerExpense(expense, user_id);
      return ResponseEntity.status(HttpStatus.CREATED).body("Expense registered successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error registering expense: " + e.getMessage());
    }
  }

  @GetMapping("")
  public ResponseEntity<Iterable<ExpenseEntity>> getAllExpenses() {
    return ResponseEntity.ok(expenseService.getAllExpenses());

  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseEntity> getExpenseById(@PathVariable Long id) {
    return expenseService.findById(id)
        .map(expense -> ResponseEntity.ok(expense))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<ResponseExpenseDTO>> getExpensesByUserId(@PathVariable Long id) {
    return expenseService.getExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expenses)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/total")
  public ResponseEntity<String> getTotalExpense(@PathVariable Long id) {
    return expenseService.getTotalExpense(id)
        .map(total -> ResponseEntity.status(HttpStatus.OK).body("Your total expense is: " + total + " $"))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-new")
  public ResponseEntity<List<ResponseExpenseDTO>> getNewExpensesByUserId(@PathVariable Long id) {
    return expenseService.getNewExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expenses)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-old")
  public ResponseEntity<List<ResponseExpenseDTO>> getOldExpensesByUserId(@PathVariable Long id) {
    return expenseService.getOldExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expenses)))
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
