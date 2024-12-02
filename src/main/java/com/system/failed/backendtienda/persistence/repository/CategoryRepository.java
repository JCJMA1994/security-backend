package com.system.failed.backendtienda.persistence.repository;

import com.system.failed.backendtienda.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
