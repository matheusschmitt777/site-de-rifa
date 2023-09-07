package com.springboot.sitederifa.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.sitederifa.entities.Client;
import com.springboot.sitederifa.entities.OrderItem;
import com.springboot.sitederifa.repositories.OrderItemRepository;
import com.springboot.sitederifa.services.exceptions.DatabaseException;
import com.springboot.sitederifa.services.exceptions.ExceededRaffleLimitException;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private RaffleNumberService raffleNumberService;
	
	@Transactional
	public OrderItem createOrderItem(OrderItem orderItem) {
	    Long raffleId = orderItem.getRaffle().getId();
	    int maxNumber = orderItem.getRaffle().getQuantity();
	    int quantity = orderItem.getQuantity();
	    Client client = orderItem.getOrder().getClient();

	    try {
	        Set<Integer> generatedNumbers = raffleNumberService.generateNumbers(raffleId, client, quantity, maxNumber);
	        orderItem.setGeneratedNumbers(generatedNumbers);
	        return orderItemRepository.save(orderItem);
	    } catch (ExceededRaffleLimitException e) {
	        throw new DatabaseException(e.getMessage());
	    }
	}
}
