package com.telros.user_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое при отсутствии запрашиваемого ресурса в системе.
 * Автоматически возвращает HTTP статус 404 (Not Found) при использовании в Spring MVC.
 *
 * <p>Примеры использования:</p>
 * // Простое сообщение
 * throw new UserNotFoundException("User not found with id: 123");
 *
 * // Структурированное сообщение
 * throw new UserNotFoundException("User", "id", 123);
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  /**
   * Создает исключение с кастомным сообщением
   * @param message детальное описание ошибки
   */
  public UserNotFoundException(String message) {
    super(message);
  }
}
