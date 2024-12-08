package com.system.failed.backendtienda.persistence.repository;

import com.system.failed.backendtienda.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
