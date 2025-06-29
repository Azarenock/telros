package com.telros.user_management.dto;

import java.time.LocalDate;

/**
 * DTO для передачи данных пользователя
 * Содержит основную информацию о пользователе
 */
public class UserDTO {
  private Long id;
  private String lastName;
  private String firstName;
  private String middleName;
  private LocalDate birthDate;
  private String email;
  private String phone;

  public UserDTO() {
  }

  public UserDTO(Long id, String lastName, String firstName, String middleName, LocalDate birthDate,
      String email, String phone) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.birthDate = birthDate;
    this.email = email;
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
