package com.telros.user_management.service.impl;

import com.telros.user_management.dto.UserDTO;
import java.util.List;
/**
 * Сервис для работы с пользователями
 */
public interface UserService {
  /**
   * Получает список всех пользователей
   * @return список DTO пользователей
   */
  List<UserDTO> getAllUsers();

  /**
   * Находит пользователя по идентификатору
   * @param id идентификатор пользователя
   * @return DTO пользователя
   */
  UserDTO getUserById(Long id);

  /**
   * Создает нового пользователя
   * @param userDTO DTO с данными нового пользователя
   * @return созданное DTO пользователя
   */
  UserDTO createUser(UserDTO userDTO);

  /**
   * Обновляет данные существующего пользователя
   * @param id идентификатор пользователя
   * @param userDTO DTO с новыми данными
   * @return обновленное DTO пользователя
   */
  UserDTO updateUser(Long id, UserDTO userDTO);

  /**
   * Удаляет пользователя по идентификатору
   * @param id идентификатор удаляемого пользователя
   */
  void deleteUser(Long id);
}
