package com.epam.training.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.productservice.model.Product;
import com.epam.training.productservice.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		productRepository.save(product);
		return product;
	}

	@Override
	public Optional<Product> getProductById(long id) {

		return productRepository.findById(id);
	}

	@Override
	public Product updateProduct(long id, Product product) {
		Optional<Product> updateProd = productRepository.findById(id);
		if (updateProd.isPresent()) {
			updateProd.get().setName(product.getName());
			updateProd.get().setDescription(product.getDescription());
			updateProd.get().setUnitPrice(product.getUnitPrice());
			productRepository.save(updateProd.get());
		}
		return product;
	}

	@Override
	public Optional<Product> deleteProductById(long id) {
		Optional<Product> deleteProd = productRepository.findById(id);
		productRepository.deleteById(id);
		return deleteProd;
	}

}
