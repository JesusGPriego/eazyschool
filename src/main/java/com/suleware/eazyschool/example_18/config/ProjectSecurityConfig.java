package com.suleware.eazyschool.example_18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg"))
        .authorizeHttpRequests(
            authRequests -> authRequests.requestMatchers("/dashboard")
                .authenticated()
                .requestMatchers("/" ,
                    "/home" ,
                    "/holidays/**" ,
                    "/contact" ,
                    "/saveMsg" ,
                    "/courses" ,
                    "/about")
                .permitAll()
                .requestMatchers("assets/**")
                .permitAll()
                .requestMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated())
        .formLogin(
            formLoginConfigurer -> formLoginConfigurer.loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                .permitAll())
        .logout(logoutConfigurer -> logoutConfigurer
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .permitAll())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  InMemoryUserDetailsManager userDetailsService() {

    UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("12345")
        .roles("ADMIN" , "ADMIN")
        .build();
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("12345")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

}
