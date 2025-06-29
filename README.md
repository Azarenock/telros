###Описание проекта
Это REST API приложение, реализованное на Java с использованием Spring Boot, предоставляющее функциональность для работы с пользователями и их контактной информацией.

Основные возможности
Аутентификация (Basic Auth)

CRUD операции для пользователей

CRUD операции для детальной информации о пользователях

Валидация данных

Технологический стек
Java 17

Spring Boot 3.x

Spring Security

Hibernate

PostgreSQL

Maven

Установка и запуск
Клонируйте репозиторий:

text
git clone <ваш-репозиторий>
Настройте базу данных PostgreSQL:

Создайте базу данных

Обновите настройки в application.properties:

text
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
Запустите приложение:

text
mvn spring-boot:run
API Endpoints
Аутентификация
Используйте Basic Auth с учетными данными: admin:admin

Пользователи (User)
GET /api/users - Получить список всех пользователей

GET /api/users/{id} - Получить пользователя по ID

POST /api/users - Создать нового пользователя

PUT /api/users/{id} - Обновить пользователя

DELETE /api/users/{id} - Удалить пользователя

Детальная информация о пользователе (UserDetails)
GET /api/user-details - Получить все записи детальной информации

GET /api/user-details/{id} - Получить детальную информацию по ID

POST /api/user-details - Создать новую детальную запись

PUT /api/user-details/{id} - Обновить детальную запись

DELETE /api/user-details/{id} - Удалить детальную запись

Модели данных
User
java
{
  "id": Long,
  "username": String,
  "password": String,
  "email": String,
  "phone": String
}
UserDetails
java
{
  "id": Long,
  "firstName": String,
  "lastName": String,
  "middleName": String,
  "birthDate": LocalDate,
  "user": User
}
Тестирование
Приложение покрыто unit и integration тестами. Для запуска тестов:

text
mvn test
Postman коллекция
Коллекция Postman для тестирования API находится в корне проекта в файле UserAPI.postman_collection.json. Импортируйте этот файл в Postman для удобного тестирования всех endpoints.
