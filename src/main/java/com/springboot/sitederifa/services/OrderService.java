package com.springboot.sitederifa.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.sitederifa.entities.Client;
import com.springboot.sitederifa.entities.Order;
import com.springboot.sitederifa.entities.OrderItem;
import com.springboot.sitederifa.entities.dto.OrderDTO;
import com.springboot.sitederifa.repositories.ClientRepository;
import com.springboot.sitederifa.repositories.OrderItemRepository;
import com.springboot.sitederifa.repositories.OrderRepository;
import com.springboot.sitederifa.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientRepository userRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private RaffleNumberService raffleNumberService;

	@Transactional
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Transactional
	public Order findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Order createOrder(OrderDTO orderDTO) {
		Client client = userRepository.findById(orderDTO.getClientId())
				.orElseThrow(() -> new ResourceNotFoundException(orderDTO.getClientId()));

		Order newOrder = new Order();
		newOrder.setClient(client);
		newOrder.setMoment(Instant.now());

		return orderRepository.save(newOrder);
	}

	@Transactional
	public void delete(Long id) {
		Order order = findById(id);
		Set<OrderItem> items = order.getItems();
		for (OrderItem item : items) {
			raffleNumberService.deleteRaffleNumbers(item);
		}
		orderItemRepository.deleteAll(items);
		orderRepository.delete(order);
	}
}