package com.personal.finance_tracker.domain.services;

import java.util.List;
import java.util.Optional;

import com.personal.finance_tracker.domain.models.IncomeModel;
import com.personal.finance_tracker.infra.entidades.IncomeEntity;

public interface IncomeServiceInterface {
  List<IncomeEntity> getAllIncomes();

  Optional<List<IncomeModel>> getIncomesByUserId(Long id);

  IncomeEntity registerIncome(IncomeEntity income, Long user_id);

  boolean deleteIncome(Long id);

  Optional<IncomeEntity> findById(Long id);

  Optional<Double> getTotalIncome(Long id);

  Optional<List<IncomeModel>> getNewIncomesByUserId(Long id);

  Optional<List<IncomeModel>> getOldIncomesByUserId(Long id);
}
