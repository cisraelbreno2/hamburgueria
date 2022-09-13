package br.com.hamburgueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hamburgueria.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	public boolean existsByCpf(String cpf);
	
}
