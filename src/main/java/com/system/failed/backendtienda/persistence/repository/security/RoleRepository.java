package com.system.failed.backendtienda.persistence.repository.security;

import com.system.failed.backendtienda.persistence.entity.security.Role;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleRepository extends Repository<Role, Long> {
	Optional<Role> findByName(String name);
}
