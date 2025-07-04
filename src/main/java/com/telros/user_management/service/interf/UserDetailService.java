package com.telros.user_management.service.interf;

import com.telros.user_management.dto.request.UserDetailRequestDTO;
import com.telros.user_management.dto.response.UserDetailResponseDTO;
import java.util.List;
/**
 * Сервис для работы с дополнительными данными пользователей
 */
public interface UserDetailService {
  List<UserDetailResponseDTO> getAllActiveUserDetails();
  UserDetailResponseDTO getUserDetailById(Long id);
  UserDetailResponseDTO getUserDetailByUserId(Long userId);
  UserDetailResponseDTO createUserDetail(UserDetailRequestDTO userDetailDTO);
  UserDetailResponseDTO updateUserDetail(Long id, UserDetailRequestDTO userDetailDTO);
  void deactivateUserDetail(Long id);
}
