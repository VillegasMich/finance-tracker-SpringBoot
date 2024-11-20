package com.personal.finance_tracker.infra.wrappers;

import java.util.List;

import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.infra.dto.LoginUserDTO;
import com.personal.finance_tracker.infra.dto.RegisterUserDTO;
import com.personal.finance_tracker.infra.dto.ResponseExpenseDTO;
import com.personal.finance_tracker.infra.dto.ResponseIncomeDTO;
import com.personal.finance_tracker.infra.dto.ResponseUserDTO;
import com.personal.finance_tracker.infra.entidades.UserEntity;

public class UserWrapper {

  /**
   * Converts RegisterUserDTO to UserModel
   *
   * @param registerUserDTO
   * @return {@link UserModel}
   */
  public static UserModel fromRegisterDTOtoModel(RegisterUserDTO registerUserDTO) {
    return new UserModel(registerUserDTO.getUsername(),
        registerUserDTO.getEmail(), registerUserDTO.getPassword());
  }

  /**
   * Converts UserModel to UserEntity
   *
   * @param userModel
   * @return {@link UserEntity}
   */
  public static UserEntity fromModelToEntity(UserModel userModel) {
    UserEntity userEntity = new UserEntity(userModel.getUsername(), userModel.getPassword(), userModel.getEmail(),
        userModel.getCreatedAt());
    if (userModel.getId() != null) {
      userEntity.setId(userModel.getId());
    }
    return userEntity;
  }

  /**
   * Converts UserEntity to UserModel
   *
   * @param userEntity
   * @return {@link UserModel}
   */
  public static UserModel fromEntityToModel(UserEntity userEntity) {
    UserModel userModel = new UserModel(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
        userEntity.getPassword(), userEntity.getCreatedAt());
    userModel.setExpenses(ExpenseWrapper.fromEntityToModel(userEntity.getExpense()));
    userModel.setIncomes(IncomeWrapper.fromEntityToModel(userEntity.getIncome()));
    return userModel;
  }

  /**
   * Converts UserModel to ResponseUserDTO
   *
   * @param userModel
   * @return {@link ResponseUserDTO}
   */
  public static ResponseUserDTO fromModelToResponseDTO(UserModel userModel) {
    List<ResponseExpenseDTO> expenses = ExpenseWrapper.fromModelToResponseDTO(userModel.getExpenses());
    List<ResponseIncomeDTO> incomes = IncomeWrapper.fromModelToResponseDTO(userModel.getIncomes());
    return new ResponseUserDTO(userModel.getId(), userModel.getUsername(), userModel.getEmail(),
        userModel.getPassword(), userModel.getCreatedAt(), expenses, incomes);
  }

  /**
   * Converts LoginUserDTO to UserModel
   *
   * @param loginUserDTO
   * @return {@link UserModel}
   */
  public static UserModel fromLoginDTOtoModel(LoginUserDTO loginUserDTO) {
    if (loginUserDTO.getUsernameOrEmail().matches("^\\w+@[a-zA-Z_]+\\.[a-zA-Z_]+$")) {
      return new UserModel(null, loginUserDTO.getUsernameOrEmail(), loginUserDTO.getPassword());
    } else {
      return new UserModel(loginUserDTO.getUsernameOrEmail(), null, loginUserDTO.getPassword());
    }
  }

}
