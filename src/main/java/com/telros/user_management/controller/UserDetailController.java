package com.telros.user_management.controller;

import com.telros.user_management.dto.UserDetailDTO;
import com.telros.user_management.service.impl.UserDetailService;
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
 * Контроллер для работы с дополнительными данными пользователей
 * Обеспечивает CRUD-операции для детальной информации о пользователях
 */
@RestController
@RequestMapping("/api/user-details")
public class UserDetailController {

  private final UserDetailService userDetailService;

  @Autowired
  public UserDetailController(UserDetailService userDetailService) {
    this.userDetailService = userDetailService;
  }

  /**
   * Получение всех детальных записей
   * @return список всех детальных записей
   */
  @GetMapping
  public ResponseEntity<List<UserDetailDTO>> getAllUserDetails() {
    return ResponseEntity.ok(userDetailService.getAllUserDetails());
  }

  /**
   * Получение детальной записи по ID
   * @param id идентификатор детальной записи
   * @return детальная запись
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDetailDTO> getUserDetailById(@PathVariable Long id) {
    return ResponseEntity.ok(userDetailService.getUserDetailById(id));
  }

  /**
   * Получение детальной записи по ID пользователя
   * @param userId идентификатор пользователя
   * @return детальная запись
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<UserDetailDTO> getUserDetailByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(userDetailService.getUserDetailByUserId(userId));
  }

  /**
   * Создание новой детальной записи
   * @param userDetailDTO данные новой записи
   * @return созданная запись
   */
  @PostMapping
  public ResponseEntity<UserDetailDTO> createUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
    return ResponseEntity.ok(userDetailService.createUserDetail(userDetailDTO));
  }

  /**
   * Обновление детальной записи
   * @param id идентификатор записи
   * @param userDetailDTO новые данные
   * @return обновленная запись
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserDetailDTO> updateUserDetail(@PathVariable Long id,
      @RequestBody UserDetailDTO userDetailDTO) {
    return ResponseEntity.ok(userDetailService.updateUserDetail(id, userDetailDTO));
  }

  /**
   * Удаление детальной записи
   * @param id идентификатор записи
   * @return статус 204 (No Content)
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserDetail(@PathVariable Long id) {
    userDetailService.deleteUserDetail(id);
    return ResponseEntity.noContent().build();
  }
}
