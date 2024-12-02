package com.system.failed.backendtienda.services;

import com.system.failed.backendtienda.dto.SaveCategory;
import com.system.failed.backendtienda.persistence.entity.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface CategoryService {
	Page<Category> findAll(Pageable pageable);

	Optional<Category> findOneById(Long categoryId);

	Category createOne(@Valid SaveCategory saveCategory);

	Category updateOneById(Long categoryId, @Valid SaveCategory saveCategory);

	Category disableOneById(Long categoryId);
}
