package com.bjit.tss.config;

import jakarta.servlet.Filter;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Data
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http   .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "api/auth/login",
                        "api/auth/register/applicant"

                )
                .permitAll()
                .requestMatchers(
                        "api/upload/file-upload/image",
                        "api/upload/file-upload/resume",
                        "/api/auth/register/applicant/validation"
                )
                .hasAuthority("USER")
                .requestMatchers(
                        "/api/application/apply",
                        "api/upload/file-upload/image",
                        "api/upload/file-upload/resume"

                )
                .hasAuthority("APPLICANT")
                .requestMatchers(
                        "/api/course",
                        "/api/course/batch_code/**"
                )
                .hasAnyAuthority("ADMIN","APPLICANT")
                .requestMatchers(
                        "/api/course/**",
                        "/api/course/update/batch_code/**",
                        "/api/application/course/**",
                        "/api/approval/applicant",
                        "/api/email/send",
                        "api/auth/register/evaluator"

                )
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
