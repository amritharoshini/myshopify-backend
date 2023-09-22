package com.myshopify.userservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myshopify.userservice.dto.AuthenticationRequest;
import com.myshopify.userservice.dto.AuthenticationResponse;
import com.myshopify.userservice.dto.RegisterRequest;
import com.myshopify.userservice.dto.TokenValidationResponse;
import com.myshopify.userservice.dto.UserDto;
import com.myshopify.userservice.model.Role;
import com.myshopify.userservice.model.User;
import com.myshopify.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRepository repository;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtService jwtService;
	
	
	public Integer register(RegisterRequest request) {
		var user = User
				.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		return repository.save(user).getId();
	}

	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		UserDto userDto = UserDto.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.role(user.getRole())
				.build();
		
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse
				.builder()
				.token(jwtToken)
				.user(userDto)
				.build();
	}


	public TokenValidationResponse isTokenValid(String token) {
		String email = jwtService.extractUsername(token);
		if(email == null)
			return TokenValidationResponse
					.builder()
					.isTokenValid(false)
					.build();
		var user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
		TokenValidationResponse response = TokenValidationResponse
				.builder()
				.isTokenValid(jwtService.isTokenValid(token, user))
				.build();
		return response;
	}
}
