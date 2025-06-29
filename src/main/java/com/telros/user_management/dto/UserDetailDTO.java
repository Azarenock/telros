package com.telros.user_management.dto;

/**
 * DTO для передачи деталей пользователя
 * Содержит дополнительную информацию о пользователе, не включенную в основной UserDTO
 */
public class UserDetailDTO {
  private Long id;
  private Long userId;
  private String address;
  private String jobTitle;
  private String department;

  public UserDetailDTO() {
  }

  public UserDetailDTO(Long id, Long userId, String address, String jobTitle, String department) {
    this.id = id;
    this.userId = userId;
    this.address = address;
    this.jobTitle = jobTitle;
    this.department = department;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public String getAddress() {
    return address;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getDepartment() {
    return department;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
