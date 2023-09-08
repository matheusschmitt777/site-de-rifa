package com.springboot.sitederifa.entities.dto;

import com.springboot.sitederifa.entities.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
