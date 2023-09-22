package com.myshopify.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors().disable()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/auth/register").permitAll()
		.requestMatchers("/api/auth/authenticate").permitAll()
		.requestMatchers("/api/auth/validate").permitAll();
		
		return http.build();
	}
}
