package br.com.hamburgueria.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.hamburgueria.models.CityModel;
import lombok.Data;

@Data
public class AddressDTO {
	
	@NotBlank
	private String road;
	
	@NotBlank
	private String district;
	
	@NotBlank
	private String number;
	
	@NotNull
	private CityModel city;
}
