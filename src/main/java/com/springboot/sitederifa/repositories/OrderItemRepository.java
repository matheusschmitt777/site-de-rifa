package com.springboot.sitederifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.sitederifa.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
