package com.system.failed.backendtienda.persistence.repository.security;

import com.system.failed.backendtienda.persistence.entity.security.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("SELECT o FROM Operation o WHERE o.permitAll = true")
	List<Operation> findByPublicAccess();

	Optional<Operation> findByName(String operation);
}
