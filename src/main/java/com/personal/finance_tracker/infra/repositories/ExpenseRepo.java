package com.personal.finance_tracker.infra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.finance_tracker.infra.entidades.ExpenseEntity;

public interface ExpenseRepo extends JpaRepository<ExpenseEntity, Long> {
  List<ExpenseEntity> findAll();
}
