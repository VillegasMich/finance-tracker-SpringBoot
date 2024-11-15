package com.personal.finance_tracker.infra.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.finance_tracker.domain.models.ExpenseModel;
import com.personal.finance_tracker.domain.services.ExpenseServiceInterface;
import com.personal.finance_tracker.infra.dto.ApiResponse;
import com.personal.finance_tracker.infra.dto.ErrorResponse;
import com.personal.finance_tracker.infra.dto.RegisterExpenseDTO;
import com.personal.finance_tracker.infra.dto.ResponseExpenseDTO;
import com.personal.finance_tracker.infra.dto.ResponseTotalDTO;
import com.personal.finance_tracker.infra.wrappers.ExpenseWrapper;
import com.personal.finance_tracker.infra.handlers.ExpenseErrorHandler;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

  private final ExpenseServiceInterface expenseService;
  private final ExpenseErrorHandler expenseErrorHandler;

  public ExpenseController(ExpenseServiceInterface expenseService, ExpenseErrorHandler expenseErrorHandler) {
    this.expenseService = expenseService;
    this.expenseErrorHandler = expenseErrorHandler;
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse> registerExpense(@RequestBody RegisterExpenseDTO expense) {
    try {
      expenseErrorHandler.validateRegisterDTO(expense);
      Long userId = expense.getUserId();
      ExpenseModel expenseModel = expenseService.registerExpense(ExpenseWrapper.fromRegisterDTOToModel(expense),
          userId);
      return ResponseEntity.status(HttpStatus.CREATED).body(ExpenseWrapper.fromModelToResponseDTO(expenseModel));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ErrorResponse("Error registering expense: " + e.getMessage(),
              HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
  }

  @GetMapping("")
  public ResponseEntity<List<ResponseExpenseDTO>> getAllExpenses() {
    List<ResponseExpenseDTO> expenses = expenseService.getAllExpenses().stream()
        .map(ExpenseWrapper::fromModelToResponseDTO)
        .toList();
    return ResponseEntity.ok(expenses);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ResponseExpenseDTO> getExpenseById(@PathVariable Long id) {
    return expenseService.findById(id)
        .map(expense -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expense)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<ResponseExpenseDTO>> getExpensesByUserId(@PathVariable Long id) {
    return expenseService.getExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expenses)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/total")
  public ResponseEntity<ResponseTotalDTO> getTotalExpense(@PathVariable Long id) {
    return expenseService.getTotalExpense(id)
        .map(total -> ResponseEntity.status(HttpStatus.OK).body(new ResponseTotalDTO(total)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-new")
  public ResponseEntity<List<ResponseExpenseDTO>> getNewExpensesByUserId(@PathVariable Long id) {
    return expenseService.getNewExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expenses)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-old")
  public ResponseEntity<List<ResponseExpenseDTO>> getOldExpensesByUserId(@PathVariable Long id) {
    return expenseService.getOldExpensesByUserId(id)
        .map(expenses -> ResponseEntity.ok(ExpenseWrapper.fromModelToResponseDTO(expenses)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
    if (expenseService.deleteExpense(id)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
