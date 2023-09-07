package com.springboot.sitederifa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.sitederifa.entities.Order;
import com.springboot.sitederifa.entities.OrderItem;
import com.springboot.sitederifa.entities.Raffle;
import com.springboot.sitederifa.entities.dto.OrderItemDTO;
import com.springboot.sitederifa.services.OrderItemService;
import com.springboot.sitederifa.services.OrderService;
import com.springboot.sitederifa.services.RaffleService;

@RestController
@RequestMapping(value = "/order-items")
public class OrderItemResource {

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private RaffleService raffleService;

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO dto) {
		Order order = orderService.findById(dto.getOrderId());
		Raffle raffle = raffleService.findById(dto.getRaffleId());

		OrderItem newOrderItem = new OrderItem(order, raffle, dto.getQuantity(), raffle.getPrice());
		newOrderItem = orderItemService.createOrderItem(newOrderItem);

		OrderItemDTO responseDto = new OrderItemDTO();
		responseDto.setId(newOrderItem.getOrder().getId());
		responseDto.setGeneratedNumbers(newOrderItem.getGeneratedNumbers());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOrderItem.getOrder().getId()).toUri();

		return ResponseEntity.created(location).body(responseDto);
	}
}
