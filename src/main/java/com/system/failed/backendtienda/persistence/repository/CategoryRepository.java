package com.system.failed.backendtienda.persistence.repository;

import com.system.failed.backendtienda.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Long> {
	Page<Category> findAll(Pageable pageable);

	Optional<Category> findById(Long categoryId);

	Category save(Category category);
}
