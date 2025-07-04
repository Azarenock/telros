package com.telros.user_management.service;

import com.telros.user_management.dto.request.UserDetailRequestDTO;
import com.telros.user_management.dto.response.UserDetailResponseDTO;
import com.telros.user_management.exception.UserNotFoundException;
import com.telros.user_management.model.User;
import com.telros.user_management.model.UserDetail;
import com.telros.user_management.repository.UserDetailRepository;
import com.telros.user_management.repository.UserRepository;
import com.telros.user_management.service.interf.UserDetailService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Реализация сервиса для работы с дополнительными данными пользователей
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

  private final UserDetailRepository userDetailRepository;
  private final UserRepository userRepository;

  @Autowired
  public UserDetailServiceImpl(UserDetailRepository userDetailRepository,
      UserRepository userRepository) {
    this.userDetailRepository = userDetailRepository;
    this.userRepository = userRepository;
  }

  /**
   * Получение всех активных деталей пользователей
   * @return список DTO с деталями пользователей
   */
  @Override
  @Transactional(readOnly = true)
  public List<UserDetailResponseDTO> getAllActiveUserDetails() {
    return userDetailRepository.findAllActive()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  /**
   * Получение деталей пользователя по ID
   * @param id ID деталей пользователя
   * @return DTO с деталями пользователя
   * @throws UserNotFoundException если детали не найдены или неактивны
   */
  @Override
  @Transactional(readOnly = true)
  public UserDetailResponseDTO getUserDetailById(Long id) {
    return userDetailRepository.findActiveById(id)
        .map(this::convertToDTO)
        .orElseThrow(() -> new UserNotFoundException("User detail not found with id: " + id));
  }

  /**
   * Получение деталей по ID пользователя
   * @param userId ID пользователя
   * @return DTO с деталями пользователя
   * @throws UserNotFoundException если детали не найдены или неактивны
   */
  @Override
  @Transactional(readOnly = true)
  public UserDetailResponseDTO getUserDetailByUserId(Long userId) {
    return userDetailRepository.findActiveByUserId(userId)
        .map(this::convertToDTO)
        .orElseThrow(() -> new UserNotFoundException("User detail not found for user id: " + userId));
  }

  /**
   * Создание новых деталей пользователя
   * @param userDetailDTO DTO с данными для создания
   * @return созданные детали в формате DTO
   * @throws UserNotFoundException если связанный пользователь не найден
   */
  @Override
  @Transactional
  public UserDetailResponseDTO createUserDetail(UserDetailRequestDTO userDetailDTO) {
    // Проверка существования пользователя
    User user = userRepository.findActiveById(userDetailDTO.getUserId())
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userDetailDTO.getUserId()));

    UserDetail userDetail = convertToEntity(userDetailDTO);
    userDetail.setUser(user);  // Установка связи с пользователем
    userDetail.setActive(true); // Активация новой записи

    UserDetail savedDetail = userDetailRepository.save(userDetail);
    return convertToDTO(savedDetail);
  }

  /**
   * Обновление существующих деталей пользователя
   * @param id ID обновляемых деталей
   * @param userDetailDTO DTO с новыми данными
   * @return обновленные детали в формате DTO
   * @throws UserNotFoundException если детали не найдены
   */
  @Override
  @Transactional
  public UserDetailResponseDTO updateUserDetail(Long id, UserDetailRequestDTO userDetailDTO) {
    return userDetailRepository.findActiveById(id)
        .map(existingDetail -> {
          updateEntityFromDTO(existingDetail, userDetailDTO); // Обновление полей
          UserDetail updatedDetail = userDetailRepository.save(existingDetail);
          return convertToDTO(updatedDetail);
        })
        .orElseThrow(() -> new UserNotFoundException("User detail not found with id: " + id));
  }

  /**
   * Деактивация деталей пользователя (мягкое удаление)
   * @param id ID деталей для деактивации
   */
  @Override
  @Transactional
  public void deactivateUserDetail(Long id) {
    userDetailRepository.deactivate(id);
  }

  // region Вспомогательные методы

  /**
   * Преобразование сущности в DTO
   * @param userDetail сущность деталей пользователя
   * @return DTO с данными деталей
   */
  private UserDetailResponseDTO convertToDTO(UserDetail userDetail) {
    UserDetailResponseDTO dto = new UserDetailResponseDTO();
    dto.setId(userDetail.getId());
    dto.setUserId(userDetail.getUser().getId());
    dto.setAddress(userDetail.getAddress());
    dto.setJobTitle(userDetail.getJobTitle());
    dto.setDepartment(userDetail.getDepartment());
    dto.setActive(userDetail.isActive());
    return dto;
  }

  /**
   * Преобразование DTO в сущность
   * @param dto DTO с данными деталей
   * @return новая сущность деталей
   */
  private UserDetail convertToEntity(UserDetailRequestDTO dto) {
    UserDetail userDetail = new UserDetail();
    updateEntityFromDTO(userDetail, dto);
    return userDetail;
  }

  /**
   * Обновление полей сущности из DTO
   * @param userDetail сущность для обновления
   * @param dto DTO с новыми данными
   */
  private void updateEntityFromDTO(UserDetail userDetail, UserDetailRequestDTO dto) {
    userDetail.setAddress(dto.getAddress());
    userDetail.setJobTitle(dto.getJobTitle());
    userDetail.setDepartment(dto.getDepartment());
  }
}
