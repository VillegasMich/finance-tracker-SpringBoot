package com.personal.finance_tracker.infra.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personal.finance_tracker.domain.models.UserModel;
import com.personal.finance_tracker.domain.services.UserServiceInterface;
import com.personal.finance_tracker.infra.dto.ApiResponse;
import com.personal.finance_tracker.infra.dto.ErrorResponse;
import com.personal.finance_tracker.infra.dto.LoginUserDTO;
import com.personal.finance_tracker.infra.dto.RegisterUserDTO;
import com.personal.finance_tracker.infra.dto.ResponseTotalDTO;
import com.personal.finance_tracker.infra.dto.ResponseUserDTO;
import com.personal.finance_tracker.infra.handlers.UserErrorHandler;
import com.personal.finance_tracker.infra.wrappers.UserWrapper;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserServiceInterface userService;
  private final UserErrorHandler userErrorHandler;

  public UserController(UserServiceInterface userService, UserErrorHandler userErrorHandler) {
    this.userService = userService;
    this.userErrorHandler = userErrorHandler;
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse> register(@RequestBody RegisterUserDTO userDTO) {
    try {
      userErrorHandler.validateRegisterUserDTO(userDTO);
      UserModel userModel = userService.register(UserWrapper.fromRegisterDTOtoModel(userDTO));
      ResponseUserDTO responseUserDTO = UserWrapper.fromModelToResponseDTO(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDTO);
    } catch (IllegalArgumentException e) {
      ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    } catch (Exception e) {
      ErrorResponse errorResponse = new ErrorResponse("Error registering user: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR.value());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse> login(@RequestBody LoginUserDTO loginUserRequest) {
    try {
      userErrorHandler.validateLoginUserRequest(loginUserRequest);
      Optional<UserModel> userModel = userService.login(UserWrapper.fromLoginDTOtoModel(loginUserRequest));
      if (userModel.isPresent()) {
        ResponseUserDTO responseUserDTO = UserWrapper.fromModelToResponseDTO(userModel.get());
        return ResponseEntity.ok(responseUserDTO);
      }
      ErrorResponse errorResponse = new ErrorResponse("User not found", HttpStatus.NOT_FOUND.value());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    } catch (IllegalArgumentException e) {
      ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
  }

  @GetMapping("")
  public ResponseEntity<List<ResponseUserDTO>> getAllUsers() {
    List<ResponseUserDTO> users = userService.getAllUsers().stream()
        .map(UserWrapper::fromModelToResponseDTO)
        .toList();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/total/{id}")
  public ResponseEntity<ResponseTotalDTO> getTotal(@PathVariable Long id) {
    return userService.getTotal(id)
        .map(total -> ResponseEntity.status(HttpStatus.OK).body(new ResponseTotalDTO(total)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id) {
    return userService.findById(id)
        .map(user -> ResponseEntity.ok(UserWrapper.fromModelToResponseDTO(user)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<ResponseUserDTO> getUserByUsername(@PathVariable String username) {
    return userService.findByUsername(username)
        .map(user -> ResponseEntity.ok(UserWrapper.fromModelToResponseDTO(user)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<ResponseUserDTO> getUserByEmail(@PathVariable String email) {
    return userService.findByEmail(email)
        .map(user -> ResponseEntity.ok(UserWrapper.fromModelToResponseDTO(user)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    if (userService.findById(id).isPresent()) {
      userService.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
