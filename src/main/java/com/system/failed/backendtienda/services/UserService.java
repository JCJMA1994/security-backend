package com.system.failed.backendtienda.services;

import com.system.failed.backendtienda.dto.SaveUser;
import com.system.failed.backendtienda.persistence.entity.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	User registerOneCustomer(@Valid SaveUser newUser);
}
