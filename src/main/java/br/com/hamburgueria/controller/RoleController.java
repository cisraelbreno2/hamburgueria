package br.com.hamburgueria.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hamburgueria.dtos.RoleDTO;
import br.com.hamburgueria.models.RoleModel;
import br.com.hamburgueria.service.RoleService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ResponseEntity<Object> saveRole(@RequestBody @Valid RoleDTO roleDTO){
		var roleModel = new RoleModel();
		BeanUtils.copyProperties(roleDTO, roleModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(roleModel));
	}
	
}
