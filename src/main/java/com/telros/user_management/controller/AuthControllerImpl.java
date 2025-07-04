package com.telros.user_management.controller;

import com.telros.user_management.controller.interfaces.AuthController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для аутентификации
 * Предоставляет эндпоинты для входа в систему
 */
@RestController
@RequestMapping("/api/auth")
public class AuthControllerImpl implements AuthController {

  @Override
  @GetMapping("/login")
  public String login() {
    return "Login endpoint (use Basic Auth)";
  }
}
