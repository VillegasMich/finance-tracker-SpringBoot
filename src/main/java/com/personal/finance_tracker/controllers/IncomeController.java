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

import com.personal.finance_tracker.models.Income;
import com.personal.finance_tracker.services.IncomeServiceInterface;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

  private final IncomeServiceInterface incomeService;

  public IncomeController(IncomeServiceInterface incomeService) {
    this.incomeService = incomeService;
  }

  @PostMapping("/register/{user_id}")
  public ResponseEntity<String> registerIncome(@RequestBody Income income, @PathVariable Long user_id) {
    try {
      incomeService.registerIncome(income, user_id);
      return ResponseEntity.status(HttpStatus.CREATED).body("Income registered successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error registering income: " + e.getMessage());
    }
  }

  @GetMapping("")
  public ResponseEntity<List<Income>> getAllIncomes() {
    return ResponseEntity.ok(incomeService.getAllIncomes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
    return incomeService.findById(id)
        .map(income -> ResponseEntity.ok(income))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<Income>> getIncomesByUserId(@PathVariable Long id) {
    return incomeService.getIncomesByUserId(id)
        .map(incomes -> ResponseEntity.ok(incomes))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/total")
  public ResponseEntity<String> getTotalIncome(@PathVariable Long id) {
    return incomeService.getTotalIncome(id)
        .map(total -> ResponseEntity.status(HttpStatus.OK).body("Your total income is: " + total + " $"))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-new")
  public ResponseEntity<List<Income>> getNewIncomesByUserId(@PathVariable Long id) {
    return incomeService.getNewIncomesByUserId(id)
        .map(incomes -> ResponseEntity.ok(incomes))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-old")
  public ResponseEntity<List<Income>> getOldIncomesByUserId(@PathVariable Long id) {
    return incomeService.getOldIncomesByUserId(id)
        .map(incomes -> ResponseEntity.ok(incomes))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
    if (incomeService.deleteIncome(id)) {
      return ResponseEntity.ok("Income deleted successfully!");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
