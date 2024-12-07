package com.system.failed.backendtienda.services;

import com.system.failed.backendtienda.persistence.entity.security.Role;

import java.util.Optional;


public interface RoleService {
	Optional<Role> findDefaultRole();
}
