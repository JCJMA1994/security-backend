package com.system.failed.backendtienda.services;

import com.system.failed.backendtienda.dto.SavePermission;
import com.system.failed.backendtienda.dto.ShowPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PermissionService {

	Page<ShowPermission> findAll(Pageable pageable);

	Optional<ShowPermission> findOneById(Long permissionId);

	ShowPermission createOne(SavePermission savePermission);

	ShowPermission deleteOneById(Long permissionId);
}
