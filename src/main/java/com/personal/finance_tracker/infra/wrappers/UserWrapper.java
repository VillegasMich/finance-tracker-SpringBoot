package com.personal.finance_tracker.infra.wrappers;

import java.util.List;

import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.infra.dto.RegisterUserDTO;
import com.personal.finance_tracker.infra.dto.ResponseExpenseDTO;
import com.personal.finance_tracker.infra.dto.ResponseIncomeDTO;
import com.personal.finance_tracker.infra.dto.ResponseUserDTO;
import com.personal.finance_tracker.infra.entidades.UserEntity;

public class UserWrapper {

  public static UserModel fromRegisterDTOtoModel(RegisterUserDTO registerUserDTO) {
    return new UserModel(registerUserDTO.getUsername(),
        registerUserDTO.getEmail(), registerUserDTO.getPassword());
  }

  public static UserEntity fromModelToEntity(UserModel userModel) {
    UserEntity userEntity = new UserEntity(userModel.getUsername(), userModel.getEmail(), userModel.getPassword(),
        userModel.getCreatedAt());
    if (userModel.getId() != null) {
      userEntity.setId(userModel.getId());
    }
    return userEntity;
  }

  public static UserModel fromEntityToModel(UserEntity userEntity) {
    UserModel userModel = new UserModel(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
        userEntity.getPassword(), userEntity.getCreatedAt());
    userModel.setExpenses(ExpenseWrapper.fromEntityToModel(userEntity.getExpense()));
    userModel.setIncomes(IncomeWrapper.fromEntityToModel(userEntity.getIncome()));
    return userModel;
  }

  public static RegisterUserDTO fromModelToRegisterDTO(UserModel userModel) {
    return new RegisterUserDTO(userModel.getUsername(), userModel.getEmail(), userModel.getPassword());
  }

  public static ResponseUserDTO fromModelToResponseDTO(UserModel userModel) {
    List<ResponseExpenseDTO> expenses = ExpenseWrapper.fromModelToResponseDTO(userModel.getExpenses());
    List<ResponseIncomeDTO> incomes = IncomeWrapper.fromModelToResponseDTO(userModel.getIncomes());
    return new ResponseUserDTO(userModel.getId(), userModel.getUsername(), userModel.getEmail(),
        userModel.getPassword(), userModel.getCreatedAt(), expenses, incomes);
  }

}
