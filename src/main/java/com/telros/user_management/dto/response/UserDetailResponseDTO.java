package com.telros.user_management.dto.response;

import java.util.Objects;

public class UserDetailResponseDTO {
  private Long id;
  private Long userId;
  private String address;
  private String jobTitle;
  private String department;
  private boolean isActive;

  public Long getId() { return id; }
  public Long getUserId() { return userId; }
  public String getAddress() { return address; }
  public String getJobTitle() { return jobTitle; }
  public String getDepartment() { return department; }
  public boolean isActive() { return isActive; }

  public void setId(Long id) { this.id = id; }
  public void setUserId(Long userId) { this.userId = userId; }
  public void setAddress(String address) { this.address = address; }
  public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
  public void setDepartment(String department) { this.department = department; }
  public void setActive(boolean active) { isActive = active; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDetailResponseDTO that = (UserDetailResponseDTO) o;
    return isActive == that.isActive &&
        Objects.equals(id, that.id) &&
        Objects.equals(userId, that.userId) &&
        Objects.equals(address, that.address) &&
        Objects.equals(jobTitle, that.jobTitle) &&
        Objects.equals(department, that.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, address, jobTitle, department, isActive);
  }

  @Override
  public String toString() {
    return "UserDetailResponseDTO{" +
        "id=" + id +
        ", userId=" + userId +
        ", address='" + address + '\'' +
        ", jobTitle='" + jobTitle + '\'' +
        ", department='" + department + '\'' +
        ", isActive=" + isActive +
        '}';
  }
}
