package com.epam.training.productservice.service;

import java.util.List;
import java.util.Optional;

import com.epam.training.productservice.model.Product;

public interface ProductService {

	
	 public List<Product> getProducts();
	 public Optional<Product> getProductById(long id);
	 public Optional<Product> deleteProductById(long id);
	 public Product saveProduct(Product product);
	 public Product updateProduct(long id, Product product);


	 
}