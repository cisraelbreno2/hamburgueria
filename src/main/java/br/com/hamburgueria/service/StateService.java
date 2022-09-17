package br.com.hamburgueria.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamburgueria.models.StateModel;
import br.com.hamburgueria.repositories.StateRepository;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository;
	
	@Transactional
	public StateModel save(StateModel stateModel) {
		return stateRepository.save(stateModel);
	}
	
	@Transactional
	public void delete(StateModel stateModel) {
		stateRepository.delete(stateModel);
	}

	public Optional<StateModel> findById(Long id) {
		return stateRepository.findById(id);
	}

	public boolean existsState(String name) {
		return stateRepository.existsByName(name);
	}

	public List<StateModel> findAll() {
		return stateRepository.findAll();
	}
}
