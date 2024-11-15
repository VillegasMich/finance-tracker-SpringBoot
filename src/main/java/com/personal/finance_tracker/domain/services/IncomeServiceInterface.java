package com.personal.finance_tracker.domain.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.domain.models.IncomeModel;

public interface IncomeServiceInterface {
  List<IncomeModel> getAllIncomes();

  Optional<List<IncomeModel>> getIncomesByUserId(Long id);

  IncomeModel registerIncome(IncomeModel income, Long user_id);

  boolean deleteIncome(Long id);

  Optional<IncomeModel> findById(Long id);

  Optional<Double> getTotalIncome(Long id);

  Optional<List<IncomeModel>> getNewIncomesByUserId(Long id);

  Optional<List<IncomeModel>> getOldIncomesByUserId(Long id);
}
