package br.com.hamburgueria.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamburgueria.models.RoleModel;
import br.com.hamburgueria.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public RoleModel save(RoleModel roleModel) {
		return roleRepository.save(roleModel);
	}
	
}
