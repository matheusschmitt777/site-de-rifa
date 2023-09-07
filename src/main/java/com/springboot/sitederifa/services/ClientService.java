package com.springboot.sitederifa.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springboot.sitederifa.entities.Client;
import com.springboot.sitederifa.repositories.ClientRepository;
import com.springboot.sitederifa.services.exceptions.DatabaseException;
import com.springboot.sitederifa.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	@Transactional
	public Client findById(Long id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.get();
	}
	@Transactional
	public Client insert(Client obj) {
	    obj.setMomentCreated(Instant.now());
	    return clientRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			if (clientRepository.existsById(id)) {
				clientRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public Client update(Long id, Client obj) {
		try {
			Client entity = clientRepository.getReferenceById(id);
			updateData(entity, obj);
			return clientRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void updateData(Client entity, Client obj) {
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
		entity.setFile(obj.getFile());
		entity.setClientStatus(obj.getClientStatus());
	}
}
