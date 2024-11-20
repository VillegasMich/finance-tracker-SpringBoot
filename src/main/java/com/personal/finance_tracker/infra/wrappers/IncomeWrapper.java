package com.personal.finance_tracker.infra.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.personal.finance_tracker.domain.models.IncomeModel;
import com.personal.finance_tracker.infra.dto.RegisterIncomeDTO;
import com.personal.finance_tracker.infra.dto.ResponseIncomeDTO;
import com.personal.finance_tracker.infra.entidades.IncomeEntity;

public class IncomeWrapper {
  /**
   * Converts List<IncomeModel> to List<ResponseIncomeDTO>
   *
   * @param incomes
   * @return List<ResponseIncomeDTO>
   */
  public static List<ResponseIncomeDTO> fromModelToResponseDTO(List<IncomeModel> incomes) {
    if (incomes == null) {
      return new ArrayList<ResponseIncomeDTO>();
    }
    List<ResponseIncomeDTO> responseIncomeDTOs = incomes.stream()
        .map(income -> new ResponseIncomeDTO(income.getId(), income.getAmount(), income.getCategory(),
            income.getDescription(), income.getCreatedAt(), income.getUserId()))
        .toList();
    return responseIncomeDTOs;

  }

  /**
   * Converts IncomeModel to ResponseIncomeDTO
   *
   * @param income
   * @return {@link ResponseIncomeDTO}
   */
  public static ResponseIncomeDTO fromModelToResponseDTO(IncomeModel income) {
    return new ResponseIncomeDTO(income.getId(), income.getAmount(), income.getCategory(), income.getDescription(),
        income.getCreatedAt(), income.getUserId());
  }

  /**
   * Converts List<IncomeEntity> to List<IncomeModel>
   *
   * @param incomes
   * @return List<IncomeModel>
   */
  public static List<IncomeModel> fromEntityToModel(List<IncomeEntity> incomes) {
    if (incomes == null) {
      return new ArrayList<IncomeModel>();
    }
    List<IncomeModel> incomeModels = incomes.stream()
        .map(
            income -> new IncomeModel(income.getId(), income.getDescription(), income.getAmount(), income.getCategory(),
                income.getCreatedAt(), income.getUserId()))
        .toList();

    return incomeModels;
  }

  /**
   * Converts IncomeEntity to IncomeModel
   *
   * @param income
   * @return {@link IncomeModel}
   */
  public static IncomeModel fromEntityToModel(IncomeEntity income) {
    return new IncomeModel(income.getId(), income.getDescription(), income.getAmount(), income.getCategory(),
        income.getCreatedAt(), income.getUserId());

  }

  /**
   * Converts RegisterIncomeDTO to IncomeModel
   *
   * @param income
   * @return {@link IncomeModel}
   */
  public static IncomeModel fromRegisterDTOToModel(RegisterIncomeDTO income) {
    return new IncomeModel(income.getDescription(), income.getAmount(), income.getCategory(), null,
        income.getUserId());
  }

  /**
   * Converts IncomeModel to IncomeEntity
   *
   * @param income
   * @return {@link IncomeEntity}
   */
  public static IncomeEntity fromModelToEntity(IncomeModel income) {
    return new IncomeEntity(income.getDescription(), income.getAmount(), income.getCreatedAt(), income.getCategory(),
        null);
  }
}
