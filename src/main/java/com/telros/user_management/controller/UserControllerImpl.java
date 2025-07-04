package com.telros.user_management.controller;

import com.telros.user_management.controller.interfaces.UserController;
import com.telros.user_management.dto.request.UserRequestDTO;
import com.telros.user_management.dto.response.UserResponseDTO;
import com.telros.user_management.service.interf.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями
 * Обеспечивает CRUD-операции для сущности пользователя
 */
@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

  private final UserService userService;

  @Autowired
  public UserControllerImpl(UserService userService) {
    this.userService = userService;
  }
  /**
   * Получение списка всех пользователей
   * @return список пользователей в формате JSON
   */
  @Override
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllActiveUsers());
  }

  /**
   * Получение пользователя по идентификатору
   * @param id идентификатор пользователя
   * @return данные пользователя в формате JSON
   */
  @Override
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  /**
   * Создание нового пользователя
   * @param userDTO DTO с данными нового пользователя
   * @return данные созданного пользователя в формате JSON
   */
  @Override
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userDTO) {
    return ResponseEntity.ok(userService.createUser(userDTO));
  }

  /**
   * Обновление данных пользователя
   * @param id идентификатор обновляемого пользователя
   * @param userDTO DTO с обновленными данными пользователя
   * @return данные обновленного пользователя в формате JSON
   */
  @Override
  public ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable Long id,
      @RequestBody @Valid UserRequestDTO userDTO) {
    return ResponseEntity.ok(userService.updateUser(id, userDTO));
  }

  /**
   * Деактивация пользователя
   * @param id идентификатор пользователя для деактивации
   * @return HTTP-статус 204 No Content при успешной деактивации
   */
  @Override
  public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
    userService.deactivateUser(id);
    return ResponseEntity.noContent().build();
  }
}
