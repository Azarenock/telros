package com.telros.user_management;

import com.telros.user_management.dto.UserDetailDTO;
import com.telros.user_management.exception.UserNotFoundException;
import com.telros.user_management.model.User;
import com.telros.user_management.model.UserDetail;
import com.telros.user_management.repository.UserDetailRepository;
import com.telros.user_management.repository.UserRepository;
import com.telros.user_management.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

  @Mock
  private UserDetailRepository userDetailRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private UserDetailServiceImpl userDetailService;

  private final User testUser = new User(1L, "Ivanov", "Ivan", null, null, null, null);
  private final UserDetail testDetail = new UserDetail(1L, "Moscow", "Developer", "IT", testUser);
  private final UserDetailDTO testDetailDTO = new UserDetailDTO(1L, 1L, "Moscow", "Developer", "IT");

  @Test
  void getAllUserDetails_ShouldReturnAllDetails() {
    // Arrange
    when(userDetailRepository.findAll()).thenReturn(List.of(testDetail));
    when(modelMapper.map(testDetail, UserDetailDTO.class)).thenReturn(testDetailDTO);

    // Act
    List<UserDetailDTO> result = userDetailService.getAllUserDetails();

    // Assert
    assertEquals(1, result.size());
    assertEquals(testDetailDTO, result.get(0));
  }

  @Test
  void getUserDetailByUserId_WhenExists_ShouldReturnDetail() {
    // Arrange
    when(userDetailRepository.findByUserId(1L)).thenReturn(Optional.of(testDetail));
    when(modelMapper.map(testDetail, UserDetailDTO.class)).thenReturn(testDetailDTO);

    // Act
    UserDetailDTO result = userDetailService.getUserDetailByUserId(1L);

    // Assert
    assertEquals(testDetailDTO, result);
  }

  @Test
  void createUserDetail_WhenUserExists_ShouldCreateDetail() {
    // Arrange
    when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    when(modelMapper.map(testDetailDTO, UserDetail.class)).thenReturn(testDetail);
    when(userDetailRepository.save(testDetail)).thenReturn(testDetail);
    when(modelMapper.map(testDetail, UserDetailDTO.class)).thenReturn(testDetailDTO);

    // Act
    UserDetailDTO result = userDetailService.createUserDetail(testDetailDTO);

    // Assert
    assertEquals(testDetailDTO, result);
    verify(userDetailRepository).save(testDetail);
  }
}