package com.springboot.sitederifa.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Raffle> insert(@RequestBody Raffle obj) {
		obj = raffleservice.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		raffleservice.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Raffle> update(@PathVariable Long id, @RequestBody Raffle obj) {
		obj = raffleservice.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
