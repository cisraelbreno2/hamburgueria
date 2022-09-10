package br.com.hamburgueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hamburgueria.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long>{
}
