package com.epam.training.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.productservice.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
