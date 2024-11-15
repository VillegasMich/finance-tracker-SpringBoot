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

import com.personal.finance_tracker.domain.models.IncomeModel;
import com.personal.finance_tracker.domain.services.IncomeServiceInterface;
import com.personal.finance_tracker.infra.dto.ApiResponse;
import com.personal.finance_tracker.infra.dto.ErrorResponse;
import com.personal.finance_tracker.infra.dto.RegisterIncomeDTO;
import com.personal.finance_tracker.infra.dto.ResponseIncomeDTO;
import com.personal.finance_tracker.infra.dto.ResponseTotalDTO;
import com.personal.finance_tracker.infra.handlers.IncomeErrorHandler;
import com.personal.finance_tracker.infra.wrappers.IncomeWrapper;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

  private final IncomeServiceInterface incomeService;
  private final IncomeErrorHandler incomeErrorHandler;

  public IncomeController(IncomeServiceInterface incomeService, IncomeErrorHandler incomeErrorHandler) {
    this.incomeService = incomeService;
    this.incomeErrorHandler = incomeErrorHandler;
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse> registerIncome(@RequestBody RegisterIncomeDTO income) {
    try {
      incomeErrorHandler.validateRegisterDTO(income);
      Long userId = income.getUserId();
      IncomeModel incomeModel = incomeService.registerIncome(IncomeWrapper.fromRegisterDTOToModel(income), userId);
      ResponseIncomeDTO responseIncomeDTO = IncomeWrapper.fromModelToResponseDTO(incomeModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(responseIncomeDTO);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ErrorResponse("Error registering income: " + e.getMessage(),
              HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
  }

  @GetMapping("")
  public ResponseEntity<List<ResponseIncomeDTO>> getAllIncomes() {
    List<ResponseIncomeDTO> incomes = incomeService.getAllIncomes().stream()
        .map(IncomeWrapper::fromModelToResponseDTO)
        .toList();
    return ResponseEntity.ok(incomes);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ResponseIncomeDTO> getIncomeById(@PathVariable Long id) {
    return incomeService.findById(id)
        .map(income -> ResponseEntity.ok(IncomeWrapper.fromModelToResponseDTO(income)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<ResponseIncomeDTO>> getIncomesByUserId(@PathVariable Long id) {
    return incomeService.getIncomesByUserId(id)
        .map(incomes -> ResponseEntity.ok(IncomeWrapper.fromModelToResponseDTO(incomes)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/total")
  public ResponseEntity<ResponseTotalDTO> getTotalIncome(@PathVariable Long id) {
    return incomeService.getTotalIncome(id)
        .map(total -> ResponseEntity.status(HttpStatus.OK).body(new ResponseTotalDTO(total)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-new")
  public ResponseEntity<List<ResponseIncomeDTO>> getNewIncomesByUserId(@PathVariable Long id) {
    return incomeService.getNewIncomesByUserId(id)
        .map(incomes -> ResponseEntity.ok(IncomeWrapper.fromModelToResponseDTO(incomes)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/user/{id}/date-old")
  public ResponseEntity<List<ResponseIncomeDTO>> getOldIncomesByUserId(@PathVariable Long id) {
    return incomeService.getOldIncomesByUserId(id)
        .map(incomes -> ResponseEntity.ok(IncomeWrapper.fromModelToResponseDTO(incomes)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
    if (incomeService.deleteIncome(id)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
