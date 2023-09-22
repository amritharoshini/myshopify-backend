package com.myshopify.userservice.dto;

import com.myshopify.userservice.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
}
