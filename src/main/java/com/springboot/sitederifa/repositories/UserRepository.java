package com.springboot.sitederifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.sitederifa.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
