package br.com.hamburgueria.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoleDTO {
	
	@NotBlank
	private String role;
	
}
