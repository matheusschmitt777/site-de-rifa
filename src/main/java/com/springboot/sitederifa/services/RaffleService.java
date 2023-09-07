package com.springboot.sitederifa.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springboot.sitederifa.entities.Raffle;
import com.springboot.sitederifa.repositories.RaffleRepository;
import com.springboot.sitederifa.services.exceptions.DatabaseException;
import com.springboot.sitederifa.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RaffleService {
	
	@Autowired
	private RaffleRepository raffleRepository;
	
	@Transactional
	public List<Raffle> findAll() {
		return raffleRepository.findAll();
	}
	
	@Transactional
	public Raffle findById(Long id) {
		Optional<Raffle> obj = raffleRepository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public Raffle insert(Raffle obj) {
	    obj.setMomentCreated(Instant.now());
	    return raffleRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			if (raffleRepository.existsById(id)) {
				raffleRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	@Transactional
	public Raffle update(Long id, Raffle obj) {
		try {
			Raffle entity = raffleRepository.getReferenceById(id);
			updateData(entity, obj);
			return raffleRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void updateData(Raffle entity, Raffle obj) {
		entity.setQuantity(obj.getQuantity());
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
		entity.setRaffleStatus(obj.getRaffleStatus());
	}
}
