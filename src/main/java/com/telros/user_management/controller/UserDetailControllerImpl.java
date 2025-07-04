package com.telros.user_management.controller;

import com.telros.user_management.controller.interfaces.UserDetailController;
import com.telros.user_management.dto.request.UserDetailRequestDTO;
import com.telros.user_management.dto.response.UserDetailResponseDTO;
import com.telros.user_management.service.interf.UserDetailService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Контроллер для работы с дополнительными данными пользователей
 * Обеспечивает CRUD-операции для детальной информации о пользователях
 */
@RestController
@RequestMapping("/api/user-details")
public class UserDetailControllerImpl implements UserDetailController {

  private final UserDetailService userDetailService;

  @Autowired
  public UserDetailControllerImpl(UserDetailService userDetailService) {
    this.userDetailService = userDetailService;
  }

  /**
   * Получение всех детальных записей
   * @return список всех детальных записей
   */
  @Override
  public ResponseEntity<List<UserDetailResponseDTO>> getAllUserDetails() {
    return ResponseEntity.ok(userDetailService.getAllActiveUserDetails());
  }

  /**
   * Получение детальной информации по идентификатору
   * @param id идентификатор детальной записи
   * @return данные детальной записи в формате JSON
   */
  @Override
  public ResponseEntity<UserDetailResponseDTO> getUserDetailById(@PathVariable Long id) {
    return ResponseEntity.ok(userDetailService.getUserDetailById(id));
  }

  /**
   * Получение детальной информации по идентификатору пользователя
   * @param userId идентификатор пользователя
   * @return данные детальной записи в формате JSON
   */
  @Override
  public ResponseEntity<UserDetailResponseDTO> getUserDetailByUserId(@PathVariable Long userId) {
    return ResponseEntity.ok(userDetailService.getUserDetailByUserId(userId));
  }

  /**
   * Создание новой детальной записи
   * @param userDetailDTO DTO с данными новой детальной записи
   * @return данные созданной детальной записи в формате JSON
   */
  @Override
  public ResponseEntity<UserDetailResponseDTO> createUserDetail(
      @RequestBody @Valid UserDetailRequestDTO userDetailDTO) {
    return ResponseEntity.ok(userDetailService.createUserDetail(userDetailDTO));
  }

  /**
   * Обновление детальной информации
   * @param id идентификатор обновляемой детальной записи
   * @param userDetailDTO DTO с обновленными данными
   * @return данные обновленной детальной записи в формате JSON
   */
  @Override
  public ResponseEntity<UserDetailResponseDTO> updateUserDetail(
      @PathVariable Long id,
      @RequestBody @Valid UserDetailRequestDTO userDetailDTO) {
    return ResponseEntity.ok(userDetailService.updateUserDetail(id, userDetailDTO));
  }

  /**
   * Деактивация детальной записи
   * @param id идентификатор детальной записи для деактивации
   * @return HTTP-статус 204 No Content при успешной деактивации
   */
  @Override
  public ResponseEntity<Void> deactivateUserDetail(@PathVariable Long id) {
    userDetailService.deactivateUserDetail(id);
    return ResponseEntity.noContent().build();
  }
}
