package com.personal.finance_tracker.infra.dto;

import java.time.LocalDateTime;

public class ResponseExpenseDTO implements ApiResponse {
  private Long id;
  private Long userId;
  private String description;
  private Double amount;
  private String category;
  private LocalDateTime createdAt;

  public ResponseExpenseDTO(Long id, String description, Double amount, String category,
      LocalDateTime createdAt, Long userId) {
    this.id = id;
    this.description = description;
    this.amount = amount;
    this.category = category;
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
