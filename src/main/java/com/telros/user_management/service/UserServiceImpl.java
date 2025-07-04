package com.telros.user_management.service;

import com.telros.user_management.dto.request.UserRequestDTO;
import com.telros.user_management.dto.response.UserResponseDTO;
import com.telros.user_management.exception.UserNotFoundException;
import com.telros.user_management.model.User;
import com.telros.user_management.repository.UserDetailRepository;
import com.telros.user_management.repository.UserRepository;
import com.telros.user_management.service.interf.UserService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Реализация сервиса для работы с пользователями
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Получение всех активных пользователей
   * @return список DTO пользователей
   */
  @Override
  @Transactional(readOnly = true)
  public List<UserResponseDTO> getAllActiveUsers() {
    return userRepository.findAllActive()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  /**
   * Получение пользователя по ID
   * @param id ID пользователя
   * @return DTO пользователя
   * @throws UserNotFoundException если пользователь не найден или неактивен
   */
  @Override
  @Transactional(readOnly = true)
  public UserResponseDTO getUserById(Long id) {
    return userRepository.findActiveById(id)
        .map(this::convertToDTO)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  /**
   * Создание нового пользователя
   * @param userDTO DTO с данными пользователя
   * @return созданный пользователь в формате DTO
   */
  @Override
  @Transactional
  public UserResponseDTO createUser(UserRequestDTO userDTO) {
    User user = convertToEntity(userDTO);
    user.setActive(true); // Новый пользователь активен по умолчанию
    User savedUser = userRepository.save(user);
    return convertToDTO(savedUser);
  }

  /**
   * Обновление данных пользователя
   * @param id ID обновляемого пользователя
   * @param userDTO DTO с новыми данными
   * @return обновленный пользователь в формате DTO
   * @throws UserNotFoundException если пользователь не найден
   */
  @Override
  @Transactional
  public UserResponseDTO updateUser(Long id, UserRequestDTO userDTO) {
    return userRepository.findActiveById(id)
        .map(existingUser -> {
          updateEntityFromDTO(existingUser, userDTO); // Обновление полей
          User updatedUser = userRepository.save(existingUser);
          return convertToDTO(updatedUser);
        })
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  /**
   * Деактивация пользователя (мягкое удаление)
   * @param id ID пользователя для деактивации
   * @throws UserNotFoundException если пользователь не найден
   */
  @Override
  @Transactional
  public void deactivateUser(Long id) {
    if (!userRepository.existsActiveById(id)) {
      throw new UserNotFoundException("User not found with id: " + id);
    }
    userRepository.deactivate(id);
  }

  // region Вспомогательные методы

  /**
   * Преобразование сущности пользователя в DTO
   * @param user сущность пользователя
   * @return DTO с данными пользователя
   */
  private UserResponseDTO convertToDTO(User user) {
    UserResponseDTO dto = new UserResponseDTO();
    dto.setId(user.getId());
    dto.setLastName(user.getLastName());
    dto.setFirstName(user.getFirstName());
    dto.setMiddleName(user.getMiddleName());
    dto.setBirthDate(user.getBirthDate());
    dto.setEmail(user.getEmail());
    dto.setPhone(user.getPhone());
    dto.setActive(user.isActive());
    return dto;
  }

  /**
   * Преобразование DTO в сущность пользователя
   * @param dto DTO с данными пользователя
   * @return новая сущность пользователя
   */
  private User convertToEntity(UserRequestDTO dto) {
    User user = new User();
    updateEntityFromDTO(user, dto);
    return user;
  }

  /**
   * Обновление полей сущности из DTO
   * @param user сущность для обновления
   * @param dto DTO с новыми данными
   */
  private void updateEntityFromDTO(User user, UserRequestDTO dto) {
    user.setLastName(dto.getLastName());
    user.setFirstName(dto.getFirstName());
    user.setMiddleName(dto.getMiddleName());
    user.setBirthDate(dto.getBirthDate());
    user.setEmail(dto.getEmail());
    user.setPhone(dto.getPhone());
  }
}
