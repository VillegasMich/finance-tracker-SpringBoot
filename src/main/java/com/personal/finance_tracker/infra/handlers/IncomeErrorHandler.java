package com.personal.finance_tracker.infra.handlers;

import org.springframework.stereotype.Component;

import com.personal.finance_tracker.infra.dto.RegisterIncomeDTO;

@Component
public class IncomeErrorHandler {

  public IncomeErrorHandler() {
  }

  /**
   * Validates that the required fields in RegisterIncomeDTO are not null.
   * 
   * @param incomeDTO the DTO to validate
   * @throws IllegalArgumentException if any required field is null
   */
  public void validateRegisterDTO(RegisterIncomeDTO incomeDTO) {
    if (incomeDTO.getDescription() == null) {
      throw new IllegalArgumentException("Description is required");
    }
    if (incomeDTO.getAmount() == null) {
      throw new IllegalArgumentException("Amount is required");
    }
    // if (IncomeDTO.getCategory() == null) {
    // throw new IllegalArgumentException("Category is required");
    // }
  }

}
