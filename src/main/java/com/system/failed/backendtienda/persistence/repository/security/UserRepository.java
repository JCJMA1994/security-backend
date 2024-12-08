package com.system.failed.backendtienda.persistence.repository.security;

import com.system.failed.backendtienda.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);

	User save(User user);
}
