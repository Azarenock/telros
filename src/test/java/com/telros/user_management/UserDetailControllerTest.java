package com.telros.user_management;

import com.telros.user_management.controller.UserDetailController;
import com.telros.user_management.dto.UserDetailDTO;
import com.telros.user_management.service.impl.UserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserDetailControllerTest {

  @Mock
  private UserDetailService userDetailService;

  @InjectMocks
  private UserDetailController userDetailController;

  private MockMvc mockMvc;

  private UserDetailDTO userDetailDTO;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userDetailController).build();

    userDetailDTO = new UserDetailDTO(
        1L,
        1L,
        "123 Main St, City",
        "Software Engineer",
        "IT Department"
    );
  }

  @Test
  void getAllUserDetails_ShouldReturnListOfDetails() throws Exception {
    List<UserDetailDTO> details = Arrays.asList(userDetailDTO);
    when(userDetailService.getAllUserDetails()).thenReturn(details);

    mockMvc.perform(get("/api/user-details")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].address").value("123 Main St, City"))
        .andExpect(jsonPath("$[0].jobTitle").value("Software Engineer"));
  }

  @Test
  void getUserDetailById_ShouldReturnDetail() throws Exception {
    when(userDetailService.getUserDetailById(1L)).thenReturn(userDetailDTO);

    mockMvc.perform(get("/api/user-details/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.department").value("IT Department"));
  }

  @Test
  void getUserDetailByUserId_ShouldReturnDetail() throws Exception {
    when(userDetailService.getUserDetailByUserId(1L)).thenReturn(userDetailDTO);

    mockMvc.perform(get("/api/user-details/user/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.jobTitle").value("Software Engineer"));
  }

  @Test
  void createUserDetail_ShouldReturnCreatedDetail() throws Exception {
    when(userDetailService.createUserDetail(any(UserDetailDTO.class))).thenReturn(userDetailDTO);

    mockMvc.perform(post("/api/user-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"userId\":1,\"address\":\"123 Main St, City\"," +
                "\"jobTitle\":\"Software Engineer\",\"department\":\"IT Department\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.address").value("123 Main St, City"));
  }

  @Test
  void updateUserDetail_ShouldReturnUpdatedDetail() throws Exception {
    when(userDetailService.updateUserDetail(anyLong(), any(UserDetailDTO.class))).thenReturn(
        userDetailDTO);

    mockMvc.perform(put("/api/user-details/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"jobTitle\":\"Senior Software Engineer\",\"department\":\"Engineering\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.jobTitle").value("Software Engineer"));
  }

  @Test
  void deleteUserDetail_ShouldReturnNoContent() throws Exception {
    doNothing().when(userDetailService).deleteUserDetail(1L);

    mockMvc.perform(delete("/api/user-details/1"))
        .andExpect(status().isNoContent());
  }
}