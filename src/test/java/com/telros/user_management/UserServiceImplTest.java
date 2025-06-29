package com.telros.user_management;

import com.telros.user_management.dto.UserDTO;
import com.telros.user_management.exception.UserNotFoundException;
import com.telros.user_management.model.User;
import com.telros.user_management.model.UserDetail;
import com.telros.user_management.repository.UserDetailRepository;
import com.telros.user_management.repository.UserRepository;
import com.telros.user_management.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserDetailRepository userDetailRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private UserServiceImpl userService;

  private final User testUser = new User(1L, "Ivanov", "Ivan", "Ivanovich",
      LocalDate.of(1990, 1, 1), "ivan@test.com", "+79991234567");
  private final UserDTO testUserDTO = new UserDTO(1L, "Ivanov", "Ivan", "Ivanovich",
      LocalDate.of(1990, 1, 1), "ivan@test.com", "+79991234567");
  private final UserDetail testUserDetail = new UserDetail();

  @Test
  void getAllUsers_ShouldReturnAllUsers() {
    // Arrange
    when(userRepository.findAll()).thenReturn(List.of(testUser));
    when(modelMapper.map(testUser, UserDTO.class)).thenReturn(testUserDTO);

    // Act
    List<UserDTO> result = userService.getAllUsers();

    // Assert
    assertEquals(1, result.size());
    assertEquals(testUserDTO, result.get(0));
  }

  @Test
  void getUserById_WhenUserExists_ShouldReturnUser() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    when(modelMapper.map(testUser, UserDTO.class)).thenReturn(testUserDTO);

    // Act
    UserDTO result = userService.getUserById(1L);

    // Assert
    assertEquals(testUserDTO, result);
  }

  @Test
  void getUserById_WhenUserNotExists_ShouldThrowException() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
  }

  @Test
  void createUser_ShouldSaveAndReturnUser() {
    // Arrange
    when(modelMapper.map(testUserDTO, User.class)).thenReturn(testUser);
    when(userRepository.save(testUser)).thenReturn(testUser);
    when(modelMapper.map(testUser, UserDTO.class)).thenReturn(testUserDTO);

    // Act
    UserDTO result = userService.createUser(testUserDTO);

    // Assert
    assertEquals(testUserDTO, result);
    verify(userRepository).save(testUser);
  }

  @Test
  void deleteUser_WhenUserExistsWithDetails_ShouldDeleteBoth() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    when(userDetailRepository.findByUserId(1L)).thenReturn(Optional.of(testUserDetail));

    // Act
    userService.deleteUser(1L);

    // Assert
    verify(userDetailRepository).delete(testUserDetail);
    verify(userRepository).delete(testUser);
  }

  @Test
  void deleteUser_WhenUserExistsWithoutDetails_ShouldDeleteOnlyUser() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    when(userDetailRepository.findByUserId(1L)).thenReturn(Optional.empty());

    // Act
    userService.deleteUser(1L);

    // Assert
    verify(userDetailRepository, never()).delete(any());
    verify(userRepository).delete(testUser);
  }

  @Test
  void deleteUser_WhenUserNotExists_ShouldDoNothing() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    // Act
    userService.deleteUser(1L);

    // Assert
    verify(userDetailRepository, never()).delete(any());
    verify(userRepository, never()).delete(any());
  }
}