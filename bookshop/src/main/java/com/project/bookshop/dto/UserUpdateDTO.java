package com.project.bookshop.dto;

import javax.validation.constraints.Email; 
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserUpdateDTO {
	@NotNull
	@NotBlank
private String username;
@Email
private String email;
@NotNull
@NotBlank
@Size(min=5)
private String password;


	
}
