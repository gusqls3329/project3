package com.team5.projrental.common.security.config;

import com.team5.projrental.common.security.ex.JwtAccessDeniedHandler;
import com.team5.projrental.common.security.ex.JwtAuthenticationEntryPoint;
import com.team5.projrental.common.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                                        // 우선 임시로 전체 허용
                                        "/**",
                                        "/api/user/signup",
                                        "/api/user/signin",
                                        "/api/user/id",
                                        "/api/user/pw",
                                        "/error",
                                        "/err",
                                        "index.html",
                                        "/static/**",
                                        "/",
                                        "/swagger.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/api/user/refresh-token"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
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
