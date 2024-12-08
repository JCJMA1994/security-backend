package com.system.failed.backendtienda.persistence.repository.security;

import com.system.failed.backendtienda.persistence.entity.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<GrantedPermission, Long> {

}
