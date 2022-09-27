package br.com.hamburgueria.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hamburgueria.models.AddressModel;
import br.com.hamburgueria.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional
	public AddressModel save(AddressModel addressModel) {
		return addressRepository.save(addressModel);
	}
	
	@Transactional
	public void delete(AddressModel addressModel) {
		addressRepository.delete(addressModel);
	}
	
	public List<AddressModel> findAll(){
		return addressRepository.findAll();
	}
	
	public Optional<AddressModel> findById(Long id) {
		return addressRepository.findById(id);
	}
	
}
