package com.system.failed.backendtienda.services.impl;

import com.system.failed.backendtienda.dto.SaveUser;
import com.system.failed.backendtienda.exception.InvalidPasswordException;
import com.system.failed.backendtienda.persistence.entity.User;
import com.system.failed.backendtienda.persistence.repository.UserRepository;
import com.system.failed.backendtienda.persistence.util.Role;
import com.system.failed.backendtienda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerOneCustomer(SaveUser newUser) {

		validatePassword(newUser);


		User user = new User();
		user.setName(newUser.getName());
		user.setUsername(newUser.getUsername());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setRole(Role.ROLE_CUSTOMER);

		return userRepository.save(user);
	}

	private void validatePassword(SaveUser newUser) {
		if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
			throw new InvalidPasswordException("Password cannot be empty");
		}

		String password = newUser.getPassword();

		if (password.length() < 8 || password.length() > 20) {
			throw new InvalidPasswordException("Password must be between 8 and 20 characters");
		}

		if (!password.matches(".*[0-9].*")) {
			throw new InvalidPasswordException("Password must contain at least one number");
		}

		if (!password.matches(".*[a-z].*")) {
			throw new InvalidPasswordException("Password must contain at least one lowercase letter");
		}

		if (!password.matches(".*[A-Z].*")) {
			throw new InvalidPasswordException("Password must contain at least one uppercase letter");
		}

		if (!password.matches(".*[@#$%^&+=!].*")) {
			throw new InvalidPasswordException("Password must contain at least one special character");
		}

		if (password.contains(" ")) {
			throw new InvalidPasswordException("Password cannot contain spaces");
		}
	}
}