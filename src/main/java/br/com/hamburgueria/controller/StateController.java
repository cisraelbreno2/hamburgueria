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
		if(stateService.existsState(stateDTO.getName())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado já cadastrado.");
		}
		BeanUtils.copyProperties(stateDTO, stateModel);
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
	
	@GetMapping
	public ResponseEntity<List<StateModel>> getAllState(){
		return ResponseEntity.status(HttpStatus.OK).body(stateService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneState(@PathVariable(value = "id") Long id){
		Optional<StateModel> stateModelOptional = stateService.findById(id);
		
		if(!stateModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(stateModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateState(@PathVariable(value = "id") Long id, @RequestBody @Valid StateDTO stateDTO) {
		Optional<StateModel> stateModelOptional = stateService.findById(id);
		
		if(!stateModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado.");
		}
		
		var stateModel = new StateModel();
		BeanUtils.copyProperties(stateDTO, stateModel);
		stateModel.setId(stateModelOptional.get().getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(stateService.save(stateModel));
		
	}
	
	
}
