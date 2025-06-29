package com.telros.user_management.service.impl;

import com.telros.user_management.dto.UserDetailDTO;
import java.util.List;
/**
 * Сервис для работы с дополнительными данными пользователей
 */
public interface UserDetailService {
  /**
   * Получает список всех детальных записей пользователей
   * @return список DTO с детальными данными
   */
  List<UserDetailDTO> getAllUserDetails();

  /**
   * Находит детальные данные по их идентификатору
   * @param id идентификатор детальных данных
   * @return DTO с детальными данными
   */
  UserDetailDTO getUserDetailById(Long id);

  /**
   * Находит детальные данные по идентификатору пользователя
   * @param userId идентификатор пользователя
   * @return DTO с детальными данными
   */
  UserDetailDTO getUserDetailByUserId(Long userId);

  /**
   * Создает новые детальные данные для пользователя
   * @param userDetailDTO DTO с данными для создания
   * @return созданное DTO с детальными данными
   */
  UserDetailDTO createUserDetail(UserDetailDTO userDetailDTO);

  /**
   * Обновляет существующие детальные данные
   * @param id идентификатор обновляемых данных
   * @param userDetailDTO DTO с новыми данными
   * @return обновленное DTO
   */
  UserDetailDTO updateUserDetail(Long id, UserDetailDTO userDetailDTO);

  /**
   * Удаляет детальные данные по идентификатору
   * @param id идентификатор удаляемых данных
   */
  void deleteUserDetail(Long id);
}
