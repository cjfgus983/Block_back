package com.example.block.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .cors(cors -> {});
        // H2 DB 헤더 옵션
        http
                .headers(headers ->
                        headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)
                        )
                );
        http.authorizeHttpRequests(
                authorize -> authorize
                        //현재는 모든 요청 허용
                        .requestMatchers(request -> request.getRequestURI().startsWith("/swagger-ui")).permitAll()
                        .requestMatchers(request -> request.getRequestURI().startsWith("/auth/sign-up")).permitAll()
                        .requestMatchers(request -> request.getRequestURI().startsWith("/auth/sign-in")).permitAll()
                        .anyRequest().permitAll()
        );
        return http.build();
    }
}
