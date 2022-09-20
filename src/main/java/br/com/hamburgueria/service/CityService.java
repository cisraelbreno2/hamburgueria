package br.com.hamburgueria.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamburgueria.models.CityModel;
import br.com.hamburgueria.repositories.CityRepository;

@Service
public class CityService {
	@Autowired CityRepository cityRepository;
	
	@Transactional
	public CityModel save(CityModel cityModel) {
		return cityRepository.save(cityModel);
	}
	
	@Transactional
	public void delete(CityModel cityModel) {
		cityRepository.delete(cityModel);
	}
	
	public Optional<CityModel> findById(Long id){
		return cityRepository.findById(id);
	}
	
	public List<CityModel> findAll(){
		return cityRepository.findAll();
	}

}
