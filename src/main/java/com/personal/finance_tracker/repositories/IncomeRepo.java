package com.personal.finance_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.finance_tracker.models.Income;

public interface IncomeRepo extends JpaRepository<Income, Long> {
}
