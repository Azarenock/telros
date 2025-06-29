package com.telros.user_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для аутентификации
 * Предоставляет эндпоинты для входа в систему
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @GetMapping("/login")
  public String login() {
    return "Login endpoint (use Basic Auth)";
  }
}
