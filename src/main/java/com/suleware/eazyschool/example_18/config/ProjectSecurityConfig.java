package com.suleware.eazyschool.example_18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  private static final String ADMIN_ROLE = "ADMIN";

  @Bean
  SecurityFilterChain securityFilterChain(
      HttpSecurity http
  ) throws Exception {

    http.csrf(
        csrf -> csrf.ignoringRequestMatchers("/saveMsg")
            .ignoringRequestMatchers("/public/**")
            .ignoringRequestMatchers("/api/**")
            .ignoringRequestMatchers("/data-api/**")
            .ignoringRequestMatchers("/eazyschool/actuator/**")
    )
        .authorizeHttpRequests(
            requests -> requests.requestMatchers("/dashboard")
                .authenticated()
                .requestMatchers("/displayMessages/**")
                .hasRole(ADMIN_ROLE)
                .requestMatchers("/closeMsg/**")
                .hasRole(ADMIN_ROLE)
                .requestMatchers("/admin/**")
                .hasRole(ADMIN_ROLE)
                .requestMatchers("/eazyschool/actuator/**")
                .hasRole(ADMIN_ROLE)
                .requestMatchers("/data-api/**")
                .authenticated()
                .requestMatchers("/api/**")
                .authenticated()
                .requestMatchers("/displayProfile")
                .authenticated()
                .requestMatchers("/updateProfile")
                .authenticated()
                .requestMatchers("/", "/home")
                .permitAll()
                .requestMatchers("/holidays/**")
                .permitAll()
                // .requestMatchers("/data-api/**")
                // .permitAll()
                .requestMatchers("/contact")
                .permitAll()
                .requestMatchers("/saveMsg")
                .permitAll()
                .requestMatchers("/courses")
                .permitAll()
                .requestMatchers("/about")
                .permitAll()
                .requestMatchers("/assets/**")
                .permitAll()
                .requestMatchers("/login")
                .permitAll()
                .requestMatchers("/logout")
                .permitAll()
                .requestMatchers("/public/**")
                .permitAll()
        )
        .formLogin(
            loginConfigurer -> loginConfigurer.loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                .permitAll()
        )
        .logout(
            logoutConfigurer -> logoutConfigurer
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
        )
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
