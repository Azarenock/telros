package com.telros.user_management.controller.interfaces;

import com.telros.user_management.dto.request.UserRequestDTO;
import com.telros.user_management.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
  @GetMapping
  ResponseEntity<List<UserResponseDTO>> getAllUsers();

  @GetMapping("/{id}")
  ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id);

  @PostMapping
  ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userDTO);

  @PutMapping("/{id}")
  ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable Long id,
      @RequestBody @Valid UserRequestDTO userDTO);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deactivateUser(@PathVariable Long id);

}
