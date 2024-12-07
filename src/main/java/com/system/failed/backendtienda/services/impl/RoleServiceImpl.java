package com.system.failed.backendtienda.services.impl;

import com.system.failed.backendtienda.persistence.entity.security.Role;
import com.system.failed.backendtienda.persistence.repository.security.RoleRepository;
import com.system.failed.backendtienda.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Value("${spring.security.default.role}")
	private String defaultRole;

	@Override
	public Optional<Role> findDefaultRole() {
		return roleRepository.findByName(defaultRole);
	}
}
