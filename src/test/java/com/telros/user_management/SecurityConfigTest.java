package com.telros.user_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void securityFilterChain_WhenUnauthenticated_ShouldDenyAccess() throws Exception {
    mockMvc.perform(get("/api/users"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  void securityFilterChain_WhenAuthenticated_ShouldAllowAccess() throws Exception {
    mockMvc.perform(get("/api/users"))
        .andExpect(status().isOk());
  }

  @Test
  void securityFilterChain_WhenAuthEndpoint_ShouldAllowAccessWithoutAuth() throws Exception {
    mockMvc.perform(get("/api/auth/test"))
        .andExpect(
            status().isNotFound()); // 404 потому что endpoint не существует, но доступ разрешен
  }

  @Test
  void userDetailsService_WithWrongCredentials_ShouldNotAuthenticate() throws Exception {
    mockMvc.perform(formLogin().user("admin").password("wrong"))
        .andExpect(unauthenticated());
  }

  @Test
  void securityFilterChain_ShouldDisableCsrf() throws Exception {
    // Проверяем, что CSRF отключен, пытаясь выполнить POST без CSRF токена
    mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
        .andExpect(
            status().isUnauthorized()); // 401 потому что нет аутентификации, а не 403 из-за CSRF
  }
}
