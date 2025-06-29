package com.telros.user_management.controller;

import com.telros.user_management.dto.UserDTO;
import com.telros.user_management.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями
 * Обеспечивает CRUD-операции для сущности пользователя
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }
  /**
   * Получение списка всех пользователей
   * @return список пользователей в формате JSON
   */
  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  /**
   * Получение пользователя по ID
   * @param id идентификатор пользователя
   * @return данные пользователя
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  /**
   * Создание нового пользователя
   * @param userDTO данные нового пользователя
   * @return созданный пользователь
   */
  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(userService.createUser(userDTO));
  }

  /**
   * Обновление данных пользователя
   * @param id идентификатор пользователя
   * @param userDTO новые данные пользователя
   * @return обновленные данные
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    return ResponseEntity.ok(userService.updateUser(id, userDTO));
  }

  /**
   * Удаление пользователя
   * @param id идентификатор пользователя
   * @return статус 204 (No Content)
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
