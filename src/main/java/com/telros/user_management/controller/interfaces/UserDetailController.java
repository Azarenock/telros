package com.telros.user_management.controller.interfaces;

import com.telros.user_management.dto.request.UserDetailRequestDTO;
import com.telros.user_management.dto.response.UserDetailResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserDetailController {

  @GetMapping
  ResponseEntity<List<UserDetailResponseDTO>> getAllUserDetails();

  @GetMapping("/{id}")
  ResponseEntity<UserDetailResponseDTO> getUserDetailById(@PathVariable Long id);

  @GetMapping("/user/{userId}")
  ResponseEntity<UserDetailResponseDTO> getUserDetailByUserId(@PathVariable Long userId);

  @PostMapping
  ResponseEntity<UserDetailResponseDTO> createUserDetail(
      @RequestBody @Valid UserDetailRequestDTO userDetailDTO);

  @PutMapping("/{id}")
  ResponseEntity<UserDetailResponseDTO> updateUserDetail(
      @PathVariable Long id,
      @RequestBody @Valid UserDetailRequestDTO userDetailDTO);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deactivateUserDetail(@PathVariable Long id);
}
