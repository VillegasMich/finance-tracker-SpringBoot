package com.personal.finance_tracker.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.finance_tracker.infra.entidades.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Long> {

  List<IncomeEntity> findAll();
}
