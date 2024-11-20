package com.personal.finance_tracker.infra.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.personal.finance_tracker.domain.models.ExpenseModel;
import com.personal.finance_tracker.infra.dto.RegisterExpenseDTO;
import com.personal.finance_tracker.infra.dto.ResponseExpenseDTO;
import com.personal.finance_tracker.infra.entidades.ExpenseEntity;

public class ExpenseWrapper {
  /**
   * Converts List<ExpenseModel> to List<ResponseExpenseDTO>
   *
   * @param expenses
   * @return List<ResponseExpenseDTO>
   */
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

  /**
   * Converts List<ExpenseEntity> to List<ExpenseModel>
   * 
   * @param expenses
   * @return List<ExpenseModel>
   */
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

  /**
   * Converts ExpenseEntity to ExpenseModel
   *
   * @param expense
   * @return {@link ExpenseModel}
   */
  public static ExpenseModel fromEntityToModel(ExpenseEntity expense) {
    return new ExpenseModel(expense.getId(), expense.getDescription(), expense.getAmount(), expense.getCategory(),
        expense.getCreatedAt(), expense.getUserId());
  }

  /**
   * Converts ExpenseModel to ExpenseEntity
   *
   * @param expense
   * @return {@link ExpenseEntity}
   */
  public static ExpenseEntity fromModelToEntity(ExpenseModel expense) {
    return new ExpenseEntity(expense.getDescription(), expense.getAmount(), expense.getCreatedAt(),
        expense.getCategory(), null);
  }

  /**
   * Converts ExpenseModel to ResponseExpenseDTO
   *
   * @param expense
   * @return {@link ResponseExpenseDTO}
   */
  public static ResponseExpenseDTO fromModelToResponseDTO(ExpenseModel expense) {
    return new ResponseExpenseDTO(expense.getId(), expense.getDescription(), expense.getAmount(),
        expense.getCategory(), expense.getCreatedAt(), expense.getUserId());
  }

  /**
   * Converts RegisterExpenseDTO to ExpenseModel
   *
   * @param expense
   * @return {@link ExpenseModel}
   */
  public static ExpenseModel fromRegisterDTOToModel(RegisterExpenseDTO expense) {
    return new ExpenseModel(expense.getDescription(), expense.getAmount(), expense.getCategory(), null,
        expense.getUserId());
  }
}
