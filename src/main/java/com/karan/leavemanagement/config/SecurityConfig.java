package com.karan.leavemanagement.config;

import com.karan.leavemanagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // only for testing
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Only MANAGER can approve, reject, cancel
                        .requestMatchers("/api/v1/requests/approve/**",
                                "/api/v1/requests/reject/**",
                                "/api/v1/requests/leaves/**",
                                "/api/v1/requests/id/**",
                                "/api/v1/requests/cancel/**").hasRole("MANAGER")
                        .requestMatchers("/api/v1/requests/cancel/**").hasAnyAuthority("MANAGER", "EMPLOYEE")
                        // Only EMPLOYEE can apply leaves
                        .requestMatchers("/api/v1/requests/apply/**").hasRole("EMPLOYEE")
                        // All other /requests/** endpoints require authentication
                        .requestMatchers("/api/v1/requests/**").authenticated()
                        // Any other request is allowed
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
