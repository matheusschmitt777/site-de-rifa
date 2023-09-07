package com.springboot.sitederifa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.sitederifa.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT oi FROM OrderItem oi WHERE oi.id.raffle.id = :raffleId")
	List<OrderItem> findAllByRaffleId(Long raffleId);
}
