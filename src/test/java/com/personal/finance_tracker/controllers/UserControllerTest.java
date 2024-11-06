// package com.personal.finance_tracker.controllers;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.personal.finance_tracker.models.Expense;
// import com.personal.finance_tracker.models.Income;
// import com.personal.finance_tracker.models.User;
// import com.personal.finance_tracker.repositories.UserRepo;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
//
// import static org.hamcrest.Matchers.is;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import java.util.Collections;
//
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
// @WebMvcTest(UserController.class)
// public class UserControllerTest {
//
// // TODO: Fix this test
//
// @Autowired
// private MockMvc mockMvc;
//
// @Autowired
// private UserRepo userRepository;
//
// private ObjectMapper objectMapper;
//
// @BeforeEach
// void setUp() {
// objectMapper = new ObjectMapper();
// }
//
// @Test
// void registerUser_ShouldReturnCreatedStatus() throws Exception {
// // Create a sample user and associated Expense and Income
// User user = new User();
// user.setUsername("john_doe");
// user.setPassword("password");
// user.setEmail("john.doe@example.com");
//
// Expense expense = new Expense();
// expense.setAmount(1000.0);
// expense.setDescription("Rent");
// expense.setUser(user);
//
// Income income = new Income();
// income.setAmount(3000.0);
// income.setDescription("Salary");
// income.setUser(user);
//
// user.setExpense(Collections.singletonList(expense));
// user.setIncome(Collections.singletonList(income));
//
// // Perform POST request to register the user
// mockMvc.perform(post("/api/users/register")
// .contentType(MediaType.APPLICATION_JSON)
// .content(objectMapper.writeValueAsString(user)))
// .andExpect(status().isCreated()) // Expect HTTP 201 Created
// .andExpect(jsonPath("$.message", is("User registered successfully!"))); //
// Response body check (based on your
// // response)
// }
//
// @Test
// void registerUser_ShouldSaveUserInDatabase() {
// // Create and save a user in the database
// User user = new User();
// user.setUsername("jane_doe");
// user.setPassword("password");
// user.setEmail("jane.doe@example.com");
//
// Expense expense = new Expense();
// expense.setAmount(1200.0);
// expense.setDescription("Utility Bills");
// expense.setUser(user);
//
// Income income = new Income();
// income.setAmount(3500.0);
// income.setDescription("Salary");
// income.setUser(user);
//
// user.setExpense(Collections.singletonList(expense));
// user.setIncome(Collections.singletonList(income));
//
// // Save user in the repository
// userRepository.save(user);
//
// // Check if the user is saved in the database
// User savedUser = userRepository.findByUsername("jane_doe").orElseThrow();
// assert (savedUser != null);
// assert (savedUser.getExpense() != null);
// assert (savedUser.getIncome() != null);
// }
// }
