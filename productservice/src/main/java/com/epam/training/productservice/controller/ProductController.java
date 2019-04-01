package com.epam.training.productservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.training.productservice.controller.exception.ProductNotFoundException;
import com.epam.training.productservice.model.Product;
import com.epam.training.productservice.model.ProductReview;
import com.epam.training.productservice.service.ProductService;


@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		return ResponseEntity.ok(productService.getProducts());
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		Product product = productService.getProductById(id).orElseThrow(()-> new ProductNotFoundException("Product ID -"+id));
		HttpHeaders headers = new HttpHeaders();
		//headers.set("SharedSecret", "BasicAuth");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String url = "http://localhost:8090/reviews/"+id;
		ResponseEntity<List<ProductReview>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ProductReview>>() {
				}, id);

		List<ProductReview> reviews = response.getBody();
		product.setReviews(reviews);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
		productService.saveProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(location).body(product);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> saveOrupdateProductById(@PathVariable int id, @RequestBody Product product) {
		return new ResponseEntity<Product>(productService.updateProduct(id, product), HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Optional<Product>> deleteProductById(@PathVariable long id) {
		Optional<Product> product = productService.deleteProductById(id);
		return new ResponseEntity<Optional<Product>>(product,HttpStatus.NOT_FOUND);
	}

}
