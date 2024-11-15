package com.personal.finance_tracker.infra.dto;

import java.time.LocalDateTime;

public class ResponseIncomeDTO implements ApiResponse {
  private Long id;
  private Long userId;
  private Double amount;
  private String category;
  private String description;
  private LocalDateTime createdAt;

  public ResponseIncomeDTO(Long id, Double amount, String category, String description,
      LocalDateTime createdAt, Long userId) {
    this.id = id;
    this.amount = amount;
    this.category = category;
    this.description = description;
    this.createdAt = createdAt;
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
