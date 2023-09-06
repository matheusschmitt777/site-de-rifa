package com.springboot.sitederifa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sitederifa.entities.Client;
import com.springboot.sitederifa.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository userRepository;
	
	public List<Client> findAll() {
		return userRepository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = userRepository.findById(id);
		return obj.get();
	}
}
