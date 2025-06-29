package com.telros.user_management.exception;

/**
 * Исключение, выбрасываемое при отсутствии пользователя
 * Используется для обработки случаев, когда запрашиваемый пользователь не найден в системе
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
