package com.personal.finance_tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.finance_tracker.models.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
  List<Expense> findAll();
}
