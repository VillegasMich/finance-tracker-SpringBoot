package com.personal.finance_tracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.services.UserServiceInterface;
import com.personal.finance_tracker.dto.LoginRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserServiceInterface userService;

  public UserController(UserServiceInterface userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    try {
      userService.registerUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
    }
  }

  @PostMapping("/find-by-credentials")
  public ResponseEntity<User> findByCredentials(@RequestBody LoginRequest loginRequest) {
    return userService.findByCredentials(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
        .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/total/{id}")
  public ResponseEntity<String> getTotal(@PathVariable Long id) {
    return userService.getTotal(id)
        .map(total -> ResponseEntity.status(HttpStatus.OK).body("Your total is: " + total + " $"))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userService.findById(id)
        .map(user -> ResponseEntity.ok(user))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    return userService.findByUsername(username)
        .map(user -> ResponseEntity.ok(user))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/get-all")
  public ResponseEntity<Iterable<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    if (userService.findById(id).isPresent()) {
      userService.deleteById(id);
      return ResponseEntity.ok("User deleted successfully!");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }
  }
}
