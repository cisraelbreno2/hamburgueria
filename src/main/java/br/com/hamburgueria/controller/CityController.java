package br.com.hamburgueria.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria.dtos.CityDTO;
import br.com.hamburgueria.models.CityModel;
import br.com.hamburgueria.models.StateModel;
import br.com.hamburgueria.service.CityService;
import br.com.hamburgueria.service.StateService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	
	@PostMapping
	public ResponseEntity<Object> saveCity(@RequestBody @Valid CityDTO cityDTO) {
		var cityModel = new CityModel();
		Optional<StateModel> stateModelOptional = stateService.findById(cityDTO.getState().getId());
		if(!stateModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado.");
		}
		BeanUtils.copyProperties(cityDTO, cityModel);
		cityModel.setState(stateModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(cityModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCity(@PathVariable(value = "id") Long id) {
		Optional<CityModel> cityModelOptional = cityService.findById(id);
		if(!cityModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada");
		}
		cityService.delete(cityModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cidade deletada com sucesso.");
	}
	
	@GetMapping
	public ResponseEntity<List<CityModel>> getAllCity(){
		return ResponseEntity.status(HttpStatus.OK).body(cityService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCity(@PathVariable(value = "id") Long id) {
		Optional<CityModel> cityModelOptional = cityService.findById(id);
		if(!cityModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cityModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCity(@PathVariable(value = "id") Long id, @RequestBody @Valid CityDTO cityDTO) {
		Optional<CityModel> cityModelOptional = cityService.findById(id);
		if(!cityModelOptional.isPresent() || stateService.findById(cityDTO.getState().getId()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade/Estado não encontrada.");
		}
		var cityModel = new CityModel();
		BeanUtils.copyProperties(cityDTO, cityModel);
		cityModel.setId(cityModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(cityService.save(cityModel));
	}

}
