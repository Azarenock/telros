package com.telros.user_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности приложения
 * Настраивает:
 * - Базовую аутентификацию (Basic Auth)
 * - Правила доступа к эндпоинтам
 * - Пользователей в памяти (in-memory)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Настраивает цепочку фильтров безопасности
   * @param http объект для настройки безопасности
   * @return сконфигурированная цепочка фильтров
   * @throws Exception при ошибках конфигурации
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .httpBasic();

    return http.build();
  }

  /**
   * Создает пользователя в памяти приложения
   * @return сервис с пользователями
   */
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("admin")
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user);
  }
}
