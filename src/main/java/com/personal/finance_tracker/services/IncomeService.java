package com.personal.finance_tracker.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personal.finance_tracker.models.Income;
import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.repositories.IncomeRepo;

@Service
public class IncomeService implements IncomeServiceInterface {

  private final IncomeRepo IncomeRepo;
  private final UserService userService;

  public IncomeService(IncomeRepo IncomeRepo, UserService userService) {
    this.IncomeRepo = IncomeRepo;
    this.userService = userService;
  }

  @Override
  public List<Income> getAllIncomes() {
    return IncomeRepo.findAll();
  }

  @Override
  public Optional<Income> findById(Long id) {
    return IncomeRepo.findById(id);
  }

  @Override
  public Optional<List<Income>> getIncomesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      return Optional.ofNullable(user.get().getIncome());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Double> getTotalIncome(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      double total = 0.0;
      if (user.get().getIncome() != null) {
        List<Income> incomes = user.get().getIncome();
        for (Income income : incomes) {
          total += income.getAmount();
        }
      }
      return Optional.of(total);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<Income>> getNewIncomesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      List<Income> incomes = user.get().getIncome().stream()
          .sorted(Comparator.comparing(Income::getCreatedAt).reversed()).collect(Collectors.toList());
      return Optional.ofNullable(incomes);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<List<Income>> getOldIncomesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      List<Income> incomes = user.get().getIncome().stream()
          .sorted(Comparator.comparing(Income::getCreatedAt)).collect(Collectors.toList());
      return Optional.ofNullable(incomes);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Income registerIncome(Income income, Long user_id) throws IllegalArgumentException {
    Optional<User> user = userService.findById(user_id);
    if (user.isPresent()) {
      income.setUser(user.get());
      return IncomeRepo.save(income);
    } else {
      throw new IllegalArgumentException("User not found with id " + user_id);
    }
  }

  @Override
  public boolean deleteIncome(Long id) {
    Optional<Income> income = IncomeRepo.findById(id);
    if (income.isPresent()) {
      IncomeRepo.deleteById(id);
      return true;
    } else {
      return false;
    }

  }

}
