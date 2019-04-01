package com.epam.training.productservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epam.training.productservice.model.ProductReview;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product.review.url}")
	private String review_url;

	@Override
	public List<ProductReview> getProductReviews(Long id) {

		HttpHeaders headers = new HttpHeaders();

		headers.set("SharedSecret", "BasicAuth");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<List<ProductReview>> response = restTemplate.exchange(review_url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ProductReview>>() {
				}, id);

		List<ProductReview> reviews = response.getBody();

		return reviews;
	}

	@Override
	public ProductReview saveProductReview(long prodId, ProductReview review) {

		Map<String, ProductReview> vars = new HashMap<>();
		vars.put("review", review);

		HttpHeaders headers = new HttpHeaders();

		headers.set("API-KEY", "Basic");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ProductReview reviews = restTemplate.postForObject(review_url, review, ProductReview.class, prodId);

		return reviews;
	}

}
