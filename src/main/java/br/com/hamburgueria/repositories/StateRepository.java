package br.com.hamburgueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hamburgueria.models.StateModel;

@Repository
public interface StateRepository extends JpaRepository<StateModel, Long> {

	public boolean existsByName(String name);
	
}
