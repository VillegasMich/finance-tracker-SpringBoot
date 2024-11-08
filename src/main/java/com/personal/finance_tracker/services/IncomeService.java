package com.personal.finance_tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.finance_tracker.models.Income;
import com.personal.finance_tracker.repositories.IncomeRepo;

@Service
public class IncomeService implements IncomeServiceInterface {

  private final IncomeRepo IncomeRepo;

  public IncomeService(IncomeRepo IncomeRepo) {
    this.IncomeRepo = IncomeRepo;
  }

  public List<Income> getAllIncomes() {
    return IncomeRepo.findAll();
  }
}
