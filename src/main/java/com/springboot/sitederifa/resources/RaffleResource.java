package com.springboot.sitederifa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sitederifa.entities.Raffle;
import com.springboot.sitederifa.services.RaffleService;

@RestController
@RequestMapping(value = "/raffles")
public class RaffleResource {
	
	@Autowired
	private RaffleService raffleservice;
	
	@GetMapping
	public ResponseEntity<List<Raffle>> findAll(){
		List<Raffle> list = raffleservice.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Raffle> findById(@PathVariable Long id) {
		Raffle obj = raffleservice.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
