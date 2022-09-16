package br.com.hamburgueria.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria.dtos.StateDTO;
import br.com.hamburgueria.models.StateModel;
import br.com.hamburgueria.service.StateService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/state")
public class StateController {
	
	@Autowired
	private StateService stateService;
	
	@PostMapping
	public ResponseEntity<Object> saveState(@RequestBody @Valid StateDTO stateDTO ){
		var stateModel = new StateModel();
		BeanUtils.copyProperties(stateDTO, stateModel);
		if(stateService.existsState(stateDTO.getName())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado já cadastrado.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(stateModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteState(@PathVariable(value = "id") Long id) {
		Optional<StateModel> stateModelOptional = stateService.findById(id);
		
		if(!stateModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado.");
		}
		
		stateService.delete(stateModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Estado deletado com sucesso.");
		
	}
	
}
