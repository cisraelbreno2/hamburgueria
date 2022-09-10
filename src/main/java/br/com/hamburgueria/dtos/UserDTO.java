package br.com.hamburgueria.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.hamburgueria.models.RoleModel;
import lombok.Data;

@Data
public class UserDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String password;

	@NotBlank
	private String login;
	
	List<RoleModel> roles = new ArrayList<>();
	
}
