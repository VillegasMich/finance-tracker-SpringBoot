package com.personal.finance_tracker.domain.models;

import java.time.LocalDateTime;
import java.util.List;

public class UserModel {
  private Long id;
  private String username;
  private String email;
  private String password;
  private LocalDateTime createdAt;
  private List<ExpenseModel> expenses;
  private List<IncomeModel> incomes;

  public UserModel(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public UserModel(long id, String username, String email, String password, LocalDateTime createdAt) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void addExpense(ExpenseModel expense) {
    expenses.add(expense);
  }

  public void addIncome(IncomeModel income) {
    incomes.add(income);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<ExpenseModel> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<ExpenseModel> expenses) {
    this.expenses = expenses;
  }

  public List<IncomeModel> getIncomes() {
    return incomes;
  }

  public void setIncomes(List<IncomeModel> incomes) {
    this.incomes = incomes;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
