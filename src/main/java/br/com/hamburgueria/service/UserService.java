package br.com.hamburgueria.service;

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
	
	public Optional<UserModel> getById(Long id) {
		return userRepository.findById(id);
	}
}
