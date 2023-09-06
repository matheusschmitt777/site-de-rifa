package com.springboot.sitederifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.sitederifa.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
