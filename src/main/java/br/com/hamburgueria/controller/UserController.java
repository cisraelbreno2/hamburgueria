package br.com.hamburgueria.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value ="id") Long id){
		UserModel user = userService.getById(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}