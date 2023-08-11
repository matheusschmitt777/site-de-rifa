package com.springboot.sitederifa.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springboot.sitederifa.entities.Order;
import com.springboot.sitederifa.entities.Raffle;
import com.springboot.sitederifa.entities.User;
import com.springboot.sitederifa.repositories.OrderRepository;
import com.springboot.sitederifa.repositories.RaffleRepository;
import com.springboot.sitederifa.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private RaffleRepository raffleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Raffle r1 = new Raffle(null, 2, "Raffle1", "Raffles", 4.50, "");
		Raffle r2 = new Raffle(null, 5, "Raffle2", "Raffles", 4.50, "");
		
		raffleRepository.saveAll(Arrays.asList(r1,r2));
		
		User u1 = new User(null, "Isabelle", "9999999", "");
		User u2 = new User(null, "Matheus", "9999999", "");
		
		Order o1 = new Order(null, u1);
		Order o2 = new Order(null, u2);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1, o2));
	}

}
