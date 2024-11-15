package com.personal.finance_tracker.infra.dto;

public class ResponseTotalDTO {
  private double total;
  private String currency;

  public ResponseTotalDTO(double total) {
    this.total = total;
    this.currency = "USD";
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
