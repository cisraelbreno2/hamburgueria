package br.com.hamburgueria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hamburgueria.models.CityModel;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long>{
}
