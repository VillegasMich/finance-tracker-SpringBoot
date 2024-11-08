package com.personal.finance_tracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/get-all")
  public ResponseEntity<Iterable<Income>> getAllIncomes() {
    return ResponseEntity.ok(incomeService.getAllIncomes());
  }

}
