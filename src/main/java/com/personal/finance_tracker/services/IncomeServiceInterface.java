package com.personal.finance_tracker.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.models.Income;

public interface IncomeServiceInterface {
  List<Income> getAllIncomes();
}
