package com.telros.user_management.repository;

import com.telros.user_management.model.UserDetail;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с деталями пользователей
 * Обеспечивает доступ к данным UserDetail в базе данных
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
  @Query("SELECT ud FROM UserDetail ud WHERE ud.user.id = :userId AND ud.isActive = true")
  Optional<UserDetail> findActiveByUserId(@Param("userId") Long userId);

  @Query("SELECT ud FROM UserDetail ud WHERE ud.id = :id AND ud.isActive = true")
  Optional<UserDetail> findActiveById(@Param("id") Long id);

  @Query("SELECT ud FROM UserDetail ud WHERE ud.isActive = true")
  List<UserDetail> findAllActive();

  @Modifying
  @Query("UPDATE UserDetail ud SET ud.isActive = false WHERE ud.id = :id")
  void deactivate(@Param("id") Long id);
}
