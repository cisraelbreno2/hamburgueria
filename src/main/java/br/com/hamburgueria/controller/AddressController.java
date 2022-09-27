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

import br.com.hamburgueria.dtos.AddressDTO;
import br.com.hamburgueria.models.AddressModel;
import br.com.hamburgueria.models.CityModel;
import br.com.hamburgueria.service.AddressService;
import br.com.hamburgueria.service.CityService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CityService cityService;
	
	@PostMapping
	public ResponseEntity<Object> saveAddress(@RequestBody @Valid AddressDTO addressDTO) {
		var addressModel = new AddressModel();
		Optional<CityModel> cityModelOptional = cityService.findById(addressDTO.getCity().getId());
		if(!cityModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrado.");
		}
		BeanUtils.copyProperties(addressDTO, addressModel);
		addressModel.setCity(cityModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(addressModel));
	}
	
	@GetMapping
	public ResponseEntity<List<AddressModel>> getAllAddress() {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneAddress(@PathVariable(value = "id") Long id) {
		Optional<AddressModel> addressModelOptional = addressService.findById(id);
		if(!addressModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(addressModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAddress(@PathVariable(value = "id") Long id) {
		Optional<AddressModel> addressModelOptional = addressService.findById(id);
		if(!addressModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
		}
		addressService.delete(addressModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso.");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAddress(@PathVariable(value = "id") Long id, @RequestBody @Valid AddressDTO addressDTO) {
		Optional<AddressModel> addressModelOptional = addressService.findById(id);
		if(!addressModelOptional.isPresent() || cityService.findById(addressDTO.getCity().getId()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço/Cidade não encontrado");
		}
		var addressModel = new AddressModel();
		BeanUtils.copyProperties(addressDTO, addressModel);
		addressModel.setId(addressModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(addressService.save(addressModel));
	}
	
	
}
