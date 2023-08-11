package com.springboot.sitederifa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.sitederifa.entities.Raffle;
import com.springboot.sitederifa.repositories.RaffleRepository;

@Service
public class RaffleService {
	
	@Autowired
	private RaffleRepository raffleRepository;
	
	public List<Raffle> findAll() {
		return raffleRepository.findAll();
	}
	
	public Raffle findById(Long id) {
		Optional<Raffle> obj = raffleRepository.findById(id);
		return obj.get();
	}
}
