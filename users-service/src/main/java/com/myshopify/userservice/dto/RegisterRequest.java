package com.myshopify.userservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@NotNull(message = "first name cannot be empty")
	private String firstName;
	@NotNull(message = "last name cannot be empty")
	private String lastName;
	@NotNull(message = "email cannot be empty")
	private String email;
	@NotNull(message = "password cannot be empty")
	@Size(min = 5, max = 10, message = "password can be minimum 5 and maximum 10 characters long")
	private String password;
}
