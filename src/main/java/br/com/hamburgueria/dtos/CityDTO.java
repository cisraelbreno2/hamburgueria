package br.com.hamburgueria.dtos;

import javax.validation.constraints.NotBlank;

import br.com.hamburgueria.models.StateModel;
import lombok.Data;

@Data
public class CityDTO {
	
	@NotBlank
	private String name;
	
	private StateModel state;

}
