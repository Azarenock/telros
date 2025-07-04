package com.telros.user_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class UserDetailRequestDTO {
  @NotNull(message = "User ID cannot be null")
  private Long userId;

  @NotBlank(message = "Address cannot be blank")
  @Size(max = 200, message = "Address must be less than 200 characters")
  private String address;

  @NotBlank(message = "Job title cannot be blank")
  @Size(max = 100, message = "Job title must be less than 100 characters")
  private String jobTitle;

  @NotBlank(message = "Department cannot be blank")
  @Size(max = 100, message = "Department must be less than 100 characters")
  private String department;

  public Long getUserId() { return userId; }
  public String getAddress() { return address; }
  public String getJobTitle() { return jobTitle; }
  public String getDepartment() { return department; }

  public void setUserId(Long userId) { this.userId = userId; }
  public void setAddress(String address) { this.address = address; }
  public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
  public void setDepartment(String department) { this.department = department; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailRequestDTO that = (UserDetailRequestDTO) o;
    return Objects.equals(userId, that.userId) &&
        Objects.equals(address, that.address) &&
        Objects.equals(jobTitle, that.jobTitle) &&
        Objects.equals(department, that.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, address, jobTitle, department);
  }

  @Override
  public String toString() {
    return "UserDetailRequestDTO{" +
        "userId=" + userId +
        ", address='" + address + '\'' +
        ", jobTitle='" + jobTitle + '\'' +
        ", department='" + department + '\'' +
        '}';
  }
}
