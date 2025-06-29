package com.telros.user_management.repository;

import com.telros.user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с пользователем
 * Обеспечивает доступ к данным User в базе данных
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
