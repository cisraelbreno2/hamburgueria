package br.com.hamburgueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hamburgueria.models.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Long>{
}
