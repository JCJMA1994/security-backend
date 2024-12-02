package com.system.failed.backendtienda.services;

import com.system.failed.backendtienda.dto.SaveProduct;
import com.system.failed.backendtienda.persistence.entity.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {


	Page<Product> findAll(Pageable pageable);

	Optional<Product> findOneById(Long productId);

	Product createOne(SaveProduct saveProduct);

	Product updateOneById(Long productId, @Valid SaveProduct saveProduct);

	Product disableOneById(Long productId);
}
