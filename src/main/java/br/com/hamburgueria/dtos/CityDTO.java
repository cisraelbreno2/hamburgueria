package br.com.hamburgueria.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.hamburgueria.models.StateModel;
import lombok.Data;

@Data
public class CityDTO {
	
	@NotBlank
	private String name;
	
	@NotNull
	private StateModel state;

}
