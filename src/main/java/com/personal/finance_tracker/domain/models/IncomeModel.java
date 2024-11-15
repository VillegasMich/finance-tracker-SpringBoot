package com.personal.finance_tracker.domain.models;

import java.time.LocalDateTime;

public class IncomeModel {
  private Long id;
  private String description;
  private Double amount;
  private String category;
  private LocalDateTime createdAt;
  private Long userId;

  public IncomeModel(String description, Double amount, String category, LocalDateTime createdAt, Long userId) {
    this.description = description;
    this.amount = amount;
    this.category = category;
    this.userId = userId;
    this.createdAt = createdAt;
  }

  public IncomeModel(long id, String description, Double amount, String category, LocalDateTime createdAt,
      Long userId) {
    this.id = id;
    this.description = description;
    this.amount = amount;
    this.category = category;
    this.userId = userId;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
