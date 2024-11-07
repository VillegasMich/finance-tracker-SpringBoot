package com.personal.finance_tracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.finance_tracker.models.Income;
import com.personal.finance_tracker.services.IncomeService;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

  private final IncomeService incomeService;

  public IncomeController(IncomeService incomeService) {
    this.incomeService = incomeService;
  }

  @GetMapping("/get-all")
  public ResponseEntity<Iterable<Income>> getAllIncomes() {
    return ResponseEntity.ok(incomeService.getAllIncomes());
  }

}
