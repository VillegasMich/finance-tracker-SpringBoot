package com.personal.finance_tracker.infra.handlers;

import org.springframework.stereotype.Component;

import com.personal.finance_tracker.infra.dto.RegisterExpenseDTO;

@Component
public class ExpenseErrorHandler {
  public ExpenseErrorHandler() {
  }

  /**
   * Validates that the required fields in RegisterIncomeDTO are not null.
   * 
   * @param expenseDTO the DTO to validate
   * @throws IllegalArgumentException if any required field is null
   */
  public void validateRegisterDTO(RegisterExpenseDTO expenseDTO) {
    if (expenseDTO.getDescription() == null) {
      throw new IllegalArgumentException("Description is required");
    }
    if (expenseDTO.getAmount() == null) {
      throw new IllegalArgumentException("Amount is required");
    }
    // if (IncomeDTO.getCategory() == null) {
    // throw new IllegalArgumentException("Category is required");
    // }
  }
}
