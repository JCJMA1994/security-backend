package com.system.failed.backendtienda.persistence.repository;

import com.system.failed.backendtienda.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {
	Page<Product> findAll(Pageable pageable);

	Optional<Product> findById(Long productId);

	Product save(Product product);
}
