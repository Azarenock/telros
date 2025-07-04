package com.telros.user_management.service.interf;

import com.telros.user_management.dto.request.UserRequestDTO;
import com.telros.user_management.dto.response.UserResponseDTO;
import java.util.List;
/**
 * Сервис для работы с пользователями
 */
public interface UserService {
  List<UserResponseDTO> getAllActiveUsers();
  UserResponseDTO getUserById(Long id);
  UserResponseDTO createUser(UserRequestDTO userDTO);
  UserResponseDTO updateUser(Long id, UserRequestDTO userDTO);
  void deactivateUser(Long id);
}
