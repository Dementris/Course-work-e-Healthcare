package com.cursework.ehelthcare.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/**","registration/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .defaultSuccessUrl("/account/home")
                        .loginPage("/login"))
                .authenticationProvider(authenticationProvider)
                .logout(logout -> logout.deleteCookies("dummyCookies"));
        return http.build();
    }




}
