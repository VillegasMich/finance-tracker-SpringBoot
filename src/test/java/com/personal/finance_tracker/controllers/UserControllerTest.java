
package com.personal.finance_tracker.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.personal.finance_tracker.models.User;
import com.personal.finance_tracker.services.UserService;
import com.personal.finance_tracker.dto.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;
import java.util.Collections;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  // TODO: FIX TEST

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testRegisterUser_Success() throws Exception {
    User user = new User();
    user.setUsername("testuser");
    user.setPassword("password123");

    doNothing().when(userService).registerUser(any(User.class));

    mockMvc.perform(post("/api/users/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isCreated())
        .andExpect(content().string("User registered successfully!"));
  }

  @Test
  public void testFindByCredentials_Success() throws Exception {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setUsernameOrEmail("test@example.com");
    loginRequest.setPassword("password123");

    User user = new User();
    user.setUsername("testuser");
    user.setEmail("test@example.com");

    when(userService.findByCredentials("test@example.com", "password123"))
        .thenReturn(Optional.of(user));

    mockMvc.perform(post("/api/users/find-by-credentials")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(loginRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("testuser"));
  }

  @Test
  public void testGetUserById_Success() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setUsername("testuser");

    when(userService.findById(1L)).thenReturn(Optional.of(user));

    mockMvc.perform(get("/api/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("testuser"));
  }

  @Test
  public void testGetUserByUsername_Success() throws Exception {
    User user = new User();
    user.setUsername("testuser");

    when(userService.findByUsername("testuser")).thenReturn(Optional.of(user));

    mockMvc.perform(get("/api/users/username/testuser"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("testuser"));
  }

  @Test
  public void testGetAllUsers_Success() throws Exception {
    User user = new User();
    user.setUsername("testuser");

    when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

    mockMvc.perform(get("/api/users/get-all"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].username").value("testuser"));
  }

  @Test
  public void testDeleteUser_Success() throws Exception {
    when(userService.findById(1L)).thenReturn(Optional.of(new User()));
    doNothing().when(userService).deleteById(1L);

    mockMvc.perform(delete("/api/users/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("User deleted successfully!"));
  }

  @Test
  public void testDeleteUser_NotFound() throws Exception {
    when(userService.findById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(delete("/api/users/1"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("User not found!"));
  }
}
