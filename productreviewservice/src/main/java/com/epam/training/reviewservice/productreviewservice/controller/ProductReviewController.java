package com.epam.training.reviewservice.productreviewservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.reviewservice.productreviewservice.model.ProductReview;
import com.epam.training.reviewservice.productreviewservice.service.ProductReviewService;


@RestController
public class ProductReviewController {

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping("/products/{prodId}/reviews")
	public ResponseEntity<List<ProductReview>> getProductReviews(@RequestHeader(value = "SharedSecret") String secret,
			@PathVariable Long prodId) {
		return ResponseEntity.ok(productReviewService.getProductReviews(prodId));
	}

	@PostMapping("/products/{prodId}/reviews")
	public ResponseEntity<ProductReview> saveProductReviews(String secret, @RequestBody ProductReview review,
			@PathVariable Long prodId) {
		return ResponseEntity.ok(productReviewService.saveProductReviews(review, prodId));
	}

	@DeleteMapping("/products/{prodId}/reviews/{reviewId}")
	public ResponseEntity<Boolean> deleteProductReviews(@PathVariable Long prodId, @PathVariable Long reviewId) {
		return ResponseEntity.ok(productReviewService.deleteProductReview(reviewId, prodId));
	}

	@PutMapping("/products/{prodId}/reviews/{reviewId}")
	public ResponseEntity<ProductReview> updateProductReviews(@RequestBody ProductReview review,
			@PathVariable Long reviewId, @PathVariable Long prodId) {
		ProductReview updateReview = productReviewService.updateProductReview(review, reviewId, prodId);
		return ResponseEntity.ok(updateReview);
	}

}
