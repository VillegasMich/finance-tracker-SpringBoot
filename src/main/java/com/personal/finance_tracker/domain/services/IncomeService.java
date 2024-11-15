package com.personal.finance_tracker.domain.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personal.finance_tracker.domain.models.IncomeModel;
import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.infra.entidades.IncomeEntity;
import com.personal.finance_tracker.infra.entidades.UserEntity;
import com.personal.finance_tracker.infra.repositories.IncomeRepo;
import com.personal.finance_tracker.infra.wrappers.IncomeWrapper;
import com.personal.finance_tracker.infra.wrappers.UserWrapper;

@Service
public class IncomeService implements IncomeServiceInterface {

  private final IncomeRepo IncomeRepo;
  private final UserService userService;

  public IncomeService(IncomeRepo IncomeRepo, UserService userService) {
    this.IncomeRepo = IncomeRepo;
    this.userService = userService;
  }

  @Override
  public List<IncomeModel> getAllIncomes() {
    List<IncomeModel> incomes = IncomeRepo.findAll().stream().map(IncomeWrapper::fromEntityToModel).toList();
    return incomes;
  }

  @Override
  public Optional<IncomeModel> findById(Long id) {
    return IncomeRepo.findById(id).map(IncomeWrapper::fromEntityToModel);
  }

  @Override
  public Optional<List<IncomeModel>> getIncomesByUserId(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      return Optional.ofNullable(user.get().getIncomes());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Double> getTotalIncome(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      double total = 0.0;
      if (user.get().getIncomes() != null) {
        List<IncomeModel> incomes = user.get().getIncomes();
        for (IncomeModel income : incomes) {
          total += income.getAmount();
        }
      }
      return Optional.of(total);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<IncomeModel>> getNewIncomesByUserId(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      List<IncomeModel> incomes = user.get().getIncomes().stream()
          .sorted(Comparator.comparing(IncomeModel::getCreatedAt).reversed()).collect(Collectors.toList());
      return Optional.ofNullable(incomes);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<IncomeModel>> getOldIncomesByUserId(Long id) {
    Optional<UserModel> user = userService.findById(id);
    if (user.isPresent()) {
      List<IncomeModel> incomes = user.get().getIncomes().stream()
          .sorted(Comparator.comparing(IncomeModel::getCreatedAt)).collect(Collectors.toList());
      return Optional.ofNullable(incomes);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public IncomeModel registerIncome(IncomeModel income, Long user_id) throws IllegalArgumentException {
    Optional<UserModel> user = userService.findById(user_id);
    if (user.isPresent()) {
      IncomeEntity incomeEntity = IncomeWrapper.fromModelToEntity(income);
      UserEntity userEntity = UserWrapper.fromModelToEntity(user.get());
      incomeEntity.setUser(userEntity);
      incomeEntity = IncomeRepo.save(incomeEntity);
      return IncomeWrapper.fromEntityToModel(incomeEntity);
    } else {
      throw new IllegalArgumentException("User not found with id " + user_id);
    }
  }

  @Override
  public boolean deleteIncome(Long id) {
    Optional<IncomeEntity> income = IncomeRepo.findById(id);
    if (income.isPresent()) {
      IncomeRepo.deleteById(id);
      return true;
    } else {
      return false;
    }

  }

}
