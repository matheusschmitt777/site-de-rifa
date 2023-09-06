package com.springboot.sitederifa.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springboot.sitederifa.entities.Client;
import com.springboot.sitederifa.entities.Order;
import com.springboot.sitederifa.entities.OrderItem;
import com.springboot.sitederifa.entities.Raffle;
import com.springboot.sitederifa.entities.enums.ClientStatus;
import com.springboot.sitederifa.entities.enums.RaffleStatus;
import com.springboot.sitederifa.repositories.ClientRepository;
import com.springboot.sitederifa.repositories.OrderItemRepository;
import com.springboot.sitederifa.repositories.OrderRepository;
import com.springboot.sitederifa.repositories.RaffleRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private RaffleRepository raffleRepository;
	
	@Autowired
	private ClientRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Raffle r1 = new Raffle(null, 2, "Raffle1", "Raffles", 4.50, "", Instant.now(), RaffleStatus.OPEN);
		Raffle r2 = new Raffle(null, 5, "Raffle2", "Raffles", 4.50, "", Instant.now(), RaffleStatus.OPEN);
		
		raffleRepository.saveAll(Arrays.asList(r1,r2));
		
		Client u1 = new Client(null, "Isabelle", "9999999", "", Instant.now(), ClientStatus.TRUE);
		Client u2 = new Client(null, "Matheus", "9999999", "", Instant.now(), ClientStatus.TRUE);
		
		Order o1 = new Order(null, Instant.now(), u1);
		Order o2 = new Order(null, Instant.now(), u2);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1, o2));
		
		OrderItem oi1 = new OrderItem(o1, r1, 4, r1.getPrice());
		OrderItem oi2 = new OrderItem(o1, r2, 1, r2.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2));
	}

}
