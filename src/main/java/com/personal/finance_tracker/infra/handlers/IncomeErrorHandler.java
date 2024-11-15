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
   * @param IncomeDTO the DTO to validate
   * @throws IllegalArgumentException if any required field is null
   */
  public void validateRegisterUserDTO(RegisterIncomeDTO IncomeDTO) {
    if (IncomeDTO.getDescription() == null) {
      throw new IllegalArgumentException("Description is required");
    }
    if (IncomeDTO.getAmount() == null) {
      throw new IllegalArgumentException("Amount is required");
    }
    // if (IncomeDTO.getCategory() == null) {
    // throw new IllegalArgumentException("Category is required");
    // }
  }

}
