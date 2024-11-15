package com.personal.finance_tracker.infra.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.personal.finance_tracker.domain.models.IncomeModel;
import com.personal.finance_tracker.infra.dto.RegisterIncomeDTO;
import com.personal.finance_tracker.infra.dto.ResponseIncomeDTO;
import com.personal.finance_tracker.infra.entidades.IncomeEntity;

public class IncomeWrapper {
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

  public static ResponseIncomeDTO fromModelToResponseDTO(IncomeModel income) {
    return new ResponseIncomeDTO(income.getId(), income.getAmount(), income.getCategory(), income.getDescription(),
        income.getCreatedAt(), income.getUserId());
  }

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

  public static IncomeModel fromEntityToModel(IncomeEntity income) {
    return new IncomeModel(income.getId(), income.getDescription(), income.getAmount(), income.getCategory(),
        income.getCreatedAt(), income.getUserId());

  }

  public static IncomeModel fromRegisterDTOToModel(RegisterIncomeDTO income) {
    return new IncomeModel(income.getDescription(), income.getAmount(), income.getCategory(), null,
        income.getUserId());
  }

  public static IncomeEntity fromModelToEntity(IncomeModel income) {
    return new IncomeEntity(income.getDescription(), income.getAmount(), income.getCreatedAt(), income.getCategory(),
        null);
  }
}
