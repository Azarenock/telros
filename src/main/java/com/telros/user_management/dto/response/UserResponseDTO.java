package com.telros.user_management.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Objects;

public class UserResponseDTO {
  private Long id;
  private String lastName;
  private String firstName;
  private String middleName;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  private String email;
  private String phone;
  private boolean isActive;

  public Long getId() { return id; }
  public String getLastName() { return lastName; }
  public String getFirstName() { return firstName; }
  public String getMiddleName() { return middleName; }
  public LocalDate getBirthDate() { return birthDate; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }
  public boolean isActive() { return isActive; }
  public String getFullName() {
    return (lastName + " " + firstName + " " + (middleName != null ? middleName : "")).trim();
  }

  public void setId(Long id) { this.id = id; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
  public void setEmail(String email) { this.email = email; }
  public void setPhone(String phone) { this.phone = phone; }
  public void setActive(boolean active) { isActive = active; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserResponseDTO that = (UserResponseDTO) o;
    return isActive == that.isActive &&
        Objects.equals(id, that.id) &&
        Objects.equals(lastName, that.lastName) &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(middleName, that.middleName) &&
        Objects.equals(birthDate, that.birthDate) &&
        Objects.equals(email, that.email) &&
        Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastName, firstName, middleName, birthDate, email, phone, isActive);
  }

  @Override
  public String toString() {
    return "UserResponseDTO{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", birthDate=" + birthDate +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", isActive=" + isActive +
        '}';
  }
}
