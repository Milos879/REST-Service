package com.project.bookshop.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class OrderDTO {

private String username;
@NotNull
private String title;
	
}
