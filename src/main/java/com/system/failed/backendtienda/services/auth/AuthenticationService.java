package com.system.failed.backendtienda.services.auth;

import com.system.failed.backendtienda.dto.RegisteredUser;
import com.system.failed.backendtienda.dto.SaveUser;
import com.system.failed.backendtienda.dto.auth.AuthenticationRequest;
import com.system.failed.backendtienda.dto.auth.AuthenticationResponse;
import com.system.failed.backendtienda.exception.ObjectNotFoundException;
import com.system.failed.backendtienda.persistence.entity.security.User;
import com.system.failed.backendtienda.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	public RegisteredUser registerOneCustomer(@Valid SaveUser newUser) {
		User user = userService.registerOneCustomer(newUser);

		RegisteredUser userDto = new RegisteredUser();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUsername(user.getUsername());
		userDto.setRole(user.getRole().getName());

		String jwt = jwtService.generateToken(user, generateExtraClaims(user));

		userDto.setJwt(jwt);

		return userDto;
	}

	private Map<String, Object> generateExtraClaims(User user) {
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("name", user.getName());
		extraClaims.put("role", user.getRole().getName());
		extraClaims.put("authorities", user.getAuthorities());

		return extraClaims;
	}

	public AuthenticationResponse login(AuthenticationRequest authRequest) {

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), authRequest.getPassword()
		);

		authenticationManager.authenticate(authentication);

		UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();
		String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

		AuthenticationResponse authRsp = new AuthenticationResponse();
		authRsp.setJwt(jwt);

		return authRsp;
	}

	public boolean validateToken(String jwt) {

		try {
			jwtService.extractUsername(jwt);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public User findLoggedInUser() {
		Authentication auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		String username = (String) auth.getPrincipal();

		return userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found" + username));

	}
}
