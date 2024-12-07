package com.system.failed.backendtienda.services;

import com.system.failed.backendtienda.dto.SaveUser;
import com.system.failed.backendtienda.persistence.entity.security.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

	User registerOneCustomer(@Valid SaveUser newUser);

	Optional<User> findOneByUsername(String username);
}
