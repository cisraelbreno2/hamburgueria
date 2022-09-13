package br.com.hamburgueria.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamburgueria.models.UserModel;
import br.com.hamburgueria.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public UserModel save(UserModel userModel) {
		return userRepository.save(userModel);
	}
	
	@Transactional
	public void delete(UserModel userModel) {
		userRepository.delete(userModel);
	}
	
	public List<UserModel> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<UserModel> findById(Long id) {
		return userRepository.findById(id);
	}

	public boolean existsCpf(String cpf) {
		return userRepository.existsByCpf(cpf);
	}
}
