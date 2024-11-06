package com.personal.finance_tracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
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

  @GetMapping("/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    return userService.findByUsername(username)
        .map(user -> ResponseEntity.ok(user))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping("/get-all")
  public ResponseEntity<Iterable<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
}
