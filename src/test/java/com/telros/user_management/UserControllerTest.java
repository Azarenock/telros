package com.telros.user_management;

import com.telros.user_management.controller.UserController;
import com.telros.user_management.dto.UserDTO;
import com.telros.user_management.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;

  private UserDTO userDTO;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    userDTO = new UserDTO(
        1L,
        "Ivanov",
        "Ivan",
        "Ivanovich",
        LocalDate.of(1990, 1, 1),
        "ivanov@example.com",
        "+1234567890"
    );
  }

  @Test
  void getAllUsers_ShouldReturnListOfUsers() throws Exception {
    List<UserDTO> users = Arrays.asList(userDTO);
    when(userService.getAllUsers()).thenReturn(users);

    mockMvc.perform(get("/api/users")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].lastName").value("Ivanov"))
        .andExpect(jsonPath("$[0].email").value("ivanov@example.com"));
  }

  @Test
  void getUserById_ShouldReturnUser() throws Exception {
    when(userService.getUserById(1L)).thenReturn(userDTO);

    mockMvc.perform(get("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Ivan"))
        .andExpect(jsonPath("$.phone").value("+1234567890"));
  }

  @Test
  void createUser_ShouldReturnCreatedUser() throws Exception {
    when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);

    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"lastName\":\"Ivanov\",\"firstName\":\"Ivan\"," +
                "\"birthDate\":\"1990-01-01\",\"email\":\"test@example.com\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastName").value("Ivanov"));
  }

  @Test
  void updateUser_ShouldReturnUpdatedUser() throws Exception {
    when(userService.updateUser(anyLong(), any(UserDTO.class))).thenReturn(userDTO);

    mockMvc.perform(put("/api/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"new@example.com\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("ivanov@example.com"));
  }

  @Test
  void deleteUser_ShouldReturnNoContent() throws Exception {
    doNothing().when(userService).deleteUser(1L);

    mockMvc.perform(delete("/api/users/1"))
        .andExpect(status().isNoContent());
  }

  @Test
  void createUser_WithEmptyFields_ShouldStillWork() throws Exception {
    UserDTO emptyUser = new UserDTO(2L, "", "", null, null, "", "");
    when(userService.createUser(any(UserDTO.class))).thenReturn(emptyUser);

    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":2}"))
        .andExpect(status().isOk());
  }
}