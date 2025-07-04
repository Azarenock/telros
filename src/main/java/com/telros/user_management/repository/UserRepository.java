package com.telros.user_management.repository;

import com.telros.user_management.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с пользователем
 * Обеспечивает доступ к данным User в базе данных
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.isActive = true")
  List<User> findAllActive();

  @Query("SELECT u FROM User u WHERE u.id = :id AND u.isActive = true")
  Optional<User> findActiveById(@Param("id") Long id);

  @Modifying
  @Query("UPDATE User u SET u.isActive = false WHERE u.id = :id")
  void deactivate(@Param("id") Long id);

  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.id = :id AND u.isActive = true")
  boolean existsActiveById(@Param("id") Long id);
}
