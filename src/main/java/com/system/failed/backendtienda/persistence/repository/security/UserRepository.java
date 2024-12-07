package com.system.failed.backendtienda.persistence.repository.security;

import com.system.failed.backendtienda.persistence.entity.security.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User,Long> {

	Optional<User> findByUsername(String username);

	User save(User user);
}
