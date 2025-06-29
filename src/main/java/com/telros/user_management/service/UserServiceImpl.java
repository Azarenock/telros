package com.telros.user_management.service;

import com.telros.user_management.dto.UserDTO;
import com.telros.user_management.exception.UserNotFoundException;
import com.telros.user_management.model.User;
import com.telros.user_management.repository.UserDetailRepository;
import com.telros.user_management.repository.UserRepository;
import com.telros.user_management.service.impl.UserService;
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
  private final UserDetailRepository userDetailRepository;
  private final ModelMapper modelMapper;

  /**
   * Конструктор с внедрением зависимостей
   * @param userRepository репозиторий пользователей
   * @param modelMapper маппер для преобразования объектов
   */
  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserDetailRepository userDetailRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.userDetailRepository = userDetailRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> modelMapper.map(user, UserDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO getUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    return modelMapper.map(user, UserDTO.class);
  }

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    User savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserDTO.class);
  }

  @Override
  public UserDTO updateUser(Long id, UserDTO userDTO) {
  User existingUser = userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

  modelMapper.map(userDTO, existingUser);
  existingUser.setId(id); // Ensure ID remains unchanged
  User updatedUser = userRepository.save(existingUser);
  return modelMapper.map(updatedUser, UserDTO.class);
  }

  @Override
  public void deleteUser(Long id) {
  userRepository.findById(id).ifPresent(user -> {
    // удаление деталей пользователя при наличии
    userDetailRepository.findByUserId(id).ifPresent(userDetailRepository::delete);
    // удаление пользователя
    userRepository.delete(user);
  });
  }
}
