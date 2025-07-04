package com.telros.user_management.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String lastName;
  private String firstName;
  private String middleName;
  private LocalDate birthDate;
  private String email;
  private String phone;
  @Column(nullable = false, columnDefinition = "boolean default true")
  private boolean isActive = true;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private UserDetail userDetail;

  public User(Long id, String lastName, String firstName, String middleName, LocalDate birthDate,
      String email, String phone) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.birthDate = birthDate;
    this.email = email;
    this.phone = phone;
  }

  public User() {

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
  public boolean isActive() {
    return isActive;
  }
  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return isActive == user.isActive && Objects.equals(id, user.id)
        && Objects.equals(lastName, user.lastName) && Objects.equals(firstName,
        user.firstName) && Objects.equals(middleName, user.middleName)
        && Objects.equals(birthDate, user.birthDate) && Objects.equals(email,
        user.email) && Objects.equals(phone, user.phone) && Objects.equals(
        userDetail, user.userDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastName, firstName, middleName, birthDate, email, phone, isActive,
        userDetail);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", birthDate=" + birthDate +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", isActive=" + isActive +
        ", userDetail=" + userDetail +
        '}';
  }
}
