package com.personal.finance_tracker.infra.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.personal.finance_tracker.domain.models.ExpenseModel;
import com.personal.finance_tracker.infra.dto.ResponseExpenseDTO;
import com.personal.finance_tracker.infra.entidades.ExpenseEntity;

public class ExpenseWrapper {
  public static List<ResponseExpenseDTO> fromModelToResponseDTO(List<ExpenseModel> expenses) {
    if (expenses == null) {
      return new ArrayList<ResponseExpenseDTO>();
    }
    List<ResponseExpenseDTO> responseExpenseDTOs = expenses.stream()
        .map(expense -> new ResponseExpenseDTO(expense.getId(), expense.getDescription(), expense.getAmount(),
            expense.getCategory(), expense.getCreatedAt(), expense.getUserId()))
        .toList();
    return responseExpenseDTOs;

  }

  public static List<ExpenseModel> fromEntityToModel(List<ExpenseEntity> expenses) {
    if (expenses == null) {
      return new ArrayList<ExpenseModel>();
    }

    List<ExpenseModel> expenseModels = expenses.stream()
        .map(expense -> new ExpenseModel(expense.getId(), expense.getDescription(), expense.getAmount(),
            expense.getCategory(), expense.getCreatedAt(), expense.getUserId()))
        .toList();

    return expenseModels;
  }
}
