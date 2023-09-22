 package com.myshopify.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myshopify.userservice.dto.AuthenticationRequest;
import com.myshopify.userservice.dto.AuthenticationResponse;
import com.myshopify.userservice.dto.RegisterRequest;
import com.myshopify.userservice.dto.RegisterResponse;
import com.myshopify.userservice.dto.TokenValidationResponse;
import com.myshopify.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
		Integer id = service.register(request);
		RegisterResponse response = RegisterResponse.builder().build();
		if(id == null) {
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Registration Unsuccessful. Please try again later");
			return ResponseEntity.badRequest().body(response);
		}
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Registration Success. Please login to proceed");
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/authenticate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	
	@GetMapping("/validate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TokenValidationResponse> isTokenValid(@RequestParam String token) {
		TokenValidationResponse response = service.isTokenValid(token);
		System.out.println(response);
		return ResponseEntity.ok().body(service.isTokenValid(token));
	}

	
}
