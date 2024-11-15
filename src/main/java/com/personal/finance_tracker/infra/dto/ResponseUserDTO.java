package com.personal.finance_tracker.infra.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseUserDTO implements ApiResponse {
  private Long id;
  private String username;
  private String email;
  private String password;
  private LocalDateTime createdAt;
  private List<ResponseExpenseDTO> expenses;
  private List<ResponseIncomeDTO> incomes;

  public ResponseUserDTO(Long id, String username, String email, String password, LocalDateTime createdAt,
      List<ResponseExpenseDTO> expenses, List<ResponseIncomeDTO> incomes) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.createdAt = createdAt;
    this.expenses = expenses;
    this.incomes = incomes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<ResponseExpenseDTO> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<ResponseExpenseDTO> expenses) {
    this.expenses = expenses;
  }

  public List<ResponseIncomeDTO> getIncomes() {
    return incomes;
  }

  public void setIncomes(List<ResponseIncomeDTO> incomes) {
    this.incomes = incomes;
  }

}
