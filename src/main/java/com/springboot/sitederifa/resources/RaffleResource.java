package com.springboot.sitederifa.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sitederifa.entities.Raffle;

@RestController
@RequestMapping(value = "/raffles")
public class RaffleResource {
	

	@GetMapping
	public ResponseEntity<Raffle> findAll(){
		Raffle u = new Raffle(1L, 2, "Robert", "Raffle1", 4.50, "");
		return ResponseEntity.ok().body(u);
	}

}
