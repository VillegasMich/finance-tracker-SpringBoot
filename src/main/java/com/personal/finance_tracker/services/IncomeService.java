package com.personal.finance_tracker.services;

import java.util.List;
import java.util.Optional;

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

  public List<Income> getAllIncomes() {
    return IncomeRepo.findAll();
  }

  public Optional<Income> findById(Long id) {
    return IncomeRepo.findById(id);
  }

  public Optional<List<Income>> getIncomesByUserId(Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isPresent()) {
      return Optional.ofNullable(user.get().getIncome());
    } else {
      return Optional.empty();
    }
  }

  public Income registerIncome(Income income, Long user_id) throws IllegalArgumentException {
    Optional<User> user = userService.findById(user_id);
    if (user.isPresent()) {
      income.setUser(user.get());
      return IncomeRepo.save(income);
    } else {
      throw new IllegalArgumentException("User not found with id " + user_id);
    }
  }

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
