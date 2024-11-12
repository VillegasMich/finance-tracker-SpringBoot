package com.personal.finance_tracker.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.models.Income;

public interface IncomeServiceInterface {
  List<Income> getAllIncomes();

  Optional<List<Income>> getIncomesByUserId(Long id);

  Income registerIncome(Income income, Long user_id);

  boolean deleteIncome(Long id);

  Optional<Income> findById(Long id);

  Optional<Double> getTotalIncome(Long id);

  Optional<List<Income>> getNewIncomesByUserId(Long id);

  Optional<List<Income>> getOldIncomesByUserId(Long id);
}
