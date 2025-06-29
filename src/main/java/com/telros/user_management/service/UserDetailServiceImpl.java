package com.telros.user_management.service;

import com.telros.user_management.dto.UserDTO;
import com.telros.user_management.dto.UserDetailDTO;
import com.telros.user_management.exception.UserNotFoundException;
import com.telros.user_management.model.User;
import com.telros.user_management.model.UserDetail;
import com.telros.user_management.repository.UserDetailRepository;
import com.telros.user_management.repository.UserRepository;
import com.telros.user_management.service.impl.UserDetailService;
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
  private final ModelMapper modelMapper;

  /**
   * Конструктор с внедрением зависимостей
   * @param userDetailRepository репозиторий детальных данных
   * @param userRepository репозиторий пользователей
   * @param modelMapper маппер для преобразования объектов
   */
  @Autowired
  public UserDetailServiceImpl(UserDetailRepository userDetailRepository,
      UserRepository userRepository,
      ModelMapper modelMapper) {
    this.userDetailRepository = userDetailRepository;
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<UserDetailDTO> getAllUserDetails() {
    return userDetailRepository.findAll().stream()
        .map(detail -> modelMapper.map(detail, UserDetailDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserDetailDTO getUserDetailById(Long id) {
    UserDetail detail = userDetailRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Детальные данные пользователя не найдены с id: " + id));
    return modelMapper.map(detail, UserDetailDTO.class);
  }

  @Override
  public UserDetailDTO getUserDetailByUserId(Long userId) {
    UserDetail detail = userDetailRepository.findByUserId(userId)
        .orElseThrow(() -> new UserNotFoundException(
            "Детальные данные не найдены для пользователя с id: " + userId));
    return modelMapper.map(detail, UserDetailDTO.class);
  }

  @Override
  public UserDetailDTO createUserDetail(UserDetailDTO userDetailDTO) {
    User user = userRepository.findById(userDetailDTO.getUserId())
        .orElseThrow(() -> new UserNotFoundException(
            "Пользователь не найден с id: " + userDetailDTO.getUserId()));

    UserDetail detail = modelMapper.map(userDetailDTO, UserDetail.class);
    detail.setUser(user);
    UserDetail savedDetail = userDetailRepository.save(detail);
    return modelMapper.map(savedDetail, UserDetailDTO.class);
  }

  @Override
  public UserDetailDTO updateUserDetail(Long id, UserDetailDTO userDetailDTO) {
    UserDetail existingDetail = userDetailRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Детальные данные пользователя не найдены с id: " + id));

    modelMapper.map(userDetailDTO, existingDetail);
    existingDetail.setId(id);
    UserDetail updatedDetail = userDetailRepository.save(existingDetail);
    return modelMapper.map(updatedDetail, UserDetailDTO.class);
  }

  @Override
  public void deleteUserDetail(Long id) {
  // удаление без исключений
  userDetailRepository.findById(id).ifPresent(userDetailRepository::delete);

  // с проверкой существования пользователя
  if (userRepository.existsById(id)) {
    userDetailRepository.deleteById(id);
  }
  }
}
