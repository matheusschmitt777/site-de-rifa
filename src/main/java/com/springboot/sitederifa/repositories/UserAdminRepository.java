package com.springboot.sitederifa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.sitederifa.entities.UserAdmin;

public interface UserAdminRepository extends JpaRepository<UserAdmin, Long>{
	
	UserDetails findByLogin(String login);

}
