package com.springboot.sitederifa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.sitederifa.entities.OrderItem;
import com.springboot.sitederifa.repositories.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Transactional
	public OrderItem createOrderItem(OrderItem orderItem) {
	    return orderItemRepository.save(orderItem);
	}
}
