package com.telros.user_management.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_details")
public class UserDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;
  private String jobTitle;
  private String department;

  @Column(nullable = false, columnDefinition = "boolean default true")
  private boolean isActive = true;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private User user;

  public UserDetail() {
  }

  public UserDetail(Long id, String address, String jobTitle, String department, User user) {
    this.id = id;
    this.address = address;
    this.jobTitle = jobTitle;
    this.department = department;
    this.user = user;
  }

  public Long getId() {
    return id;
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

  public User getUser() {
    return user;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetail that = (UserDetail) o;
    return isActive == that.isActive && Objects.equals(id, that.id)
        && Objects.equals(address, that.address) && Objects.equals(jobTitle,
        that.jobTitle) && Objects.equals(department, that.department)
        && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, jobTitle, department, isActive, user);
  }

  @Override
  public String toString() {
    return "UserDetail{" +
        "id=" + id +
        ", address='" + address + '\'' +
        ", jobTitle='" + jobTitle + '\'' +
        ", department='" + department + '\'' +
        ", isActive=" + isActive +
        ", user=" + user +
        '}';
  }
}
