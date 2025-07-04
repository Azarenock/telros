package com.telros.user_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class UserRequestDTO {
  @NotBlank(message = "Last name cannot be blank")
  @Size(max = 50, message = "Last name must be less than 50 characters")
  private String lastName;

  @NotBlank(message = "First name cannot be blank")
  @Size(max = 50, message = "First name must be less than 50 characters")
  private String firstName;

  @Size(max = 50, message = "Middle name must be less than 50 characters")
  private String middleName;

  @NotNull(message = "Birth date cannot be null")
  @Past(message = "Birth date must be in the past")
  private LocalDate birthDate;

  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  @Size(max = 100, message = "Email must be less than 100 characters")
  private String email;

  @NotBlank(message = "Phone cannot be blank")
  @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone must be 10-15 digits")
  private String phone;

  public String getLastName() { return lastName; }
  public String getFirstName() { return firstName; }
  public String getMiddleName() { return middleName; }
  public LocalDate getBirthDate() { return birthDate; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }

  public void setLastName(String lastName) { this.lastName = lastName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
  public void setEmail(String email) { this.email = email; }
  public void setPhone(String phone) { this.phone = phone; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRequestDTO that = (UserRequestDTO) o;
    return Objects.equals(lastName, that.lastName) &&
        Objects.equals(firstName, that.firstName) &&
        Objects.equals(middleName, that.middleName) &&
        Objects.equals(birthDate, that.birthDate) &&
        Objects.equals(email, that.email) &&
        Objects.equals(phone, that.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastName, firstName, middleName, birthDate, email, phone);
  }

  @Override
  public String toString() {
    return "UserRequestDTO{" +
        "lastName='" + lastName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", birthDate=" + birthDate +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }
}
