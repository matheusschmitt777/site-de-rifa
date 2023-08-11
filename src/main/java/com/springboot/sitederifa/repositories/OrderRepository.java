package com.springboot.sitederifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.sitederifa.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
