package com.team5.projrental.common.security.config;

import com.team5.projrental.common.security.ex.JwtAccessDeniedHandler;
import com.team5.projrental.common.security.ex.JwtAuthenticationEntryPoint;
import com.team5.projrental.common.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.sessionManagement(authz -> authz.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/api/mypage/**",
                        "/api/chat/**",
                        "/api/pay/review",
                        "/api/user/signout",
                        "/api/user/fcm",
                        "/api/user/refrech-token",
                        "/api/user/firebase-token"
                ).authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.PATCH, "/api/user"
                ).authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.GET, "/api/user"
                ).authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        HttpMethod.PUT, "/api/user"
                ).authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/api/pay/**"
                ).authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                HttpMethod.DELETE, "/api/prod"
                        ).authenticated()
                )
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                HttpMethod.POST, "/api/prod"
                        ).authenticated()
                )
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                HttpMethod.PUT, "/api/prod"
                        ).authenticated()
                )
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                HttpMethod.PATCH, "/api/prod"
                        ).authenticated()
                        .anyRequest().permitAll())
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
                    ex.accessDeniedHandler(new JwtAccessDeniedHandler());
                }).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
