package com.telros.user_management.repository;

import com.telros.user_management.model.UserDetail;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с деталями пользователей
 * Обеспечивает доступ к данным UserDetail в базе данных
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
  Optional<UserDetail> findByUserId(Long userId);
}
