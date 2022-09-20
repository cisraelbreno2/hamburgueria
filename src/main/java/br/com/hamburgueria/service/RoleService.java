package br.com.hamburgueria.service;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<RoleModel> findById(Long id) {
		return roleRepository.findById(id);
	}

	public List<RoleModel> findAll() {
		return roleRepository.findAll();
	}
	
}
