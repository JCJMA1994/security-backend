package com.system.failed.backendtienda.services.auth;

import com.system.failed.backendtienda.dto.RegisteredUser;
import com.system.failed.backendtienda.dto.SaveUser;
import com.system.failed.backendtienda.persistence.entity.User;
import com.system.failed.backendtienda.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	public RegisteredUser registerOneCustomer(@Valid SaveUser newUser) {
		User user = userService.registerOneCustomer(newUser);

		RegisteredUser userDto = new RegisteredUser();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUsername(user.getUsername());
		userDto.setRole(user.getRole().name());

		String jwt = jwtService.generateToken(user);

		userDto.setJwt(jwt);

		return userDto;
	}
}
