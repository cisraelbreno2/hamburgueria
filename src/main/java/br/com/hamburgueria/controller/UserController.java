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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria.dtos.UserDTO;
import br.com.hamburgueria.models.UserModel;
import br.com.hamburgueria.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO) {
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDTO, userModel);
		if(userService.existsCpf(userDTO.getCpf())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário já cadastrado.");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
	}
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value ="id") Long id){
		Optional<UserModel> userModelOptional = userService.findById(id);
		if(!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id){
		Optional<UserModel> userModelOptional = userService.findById(id);
		if(!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		
		userService.delete(userModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
	}
	
	
}