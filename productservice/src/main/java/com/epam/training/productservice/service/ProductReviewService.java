package com.epam.training.productservice.service;

import java.util.List;

import com.epam.training.productservice.model.ProductReview;

public interface ProductReviewService {

	public List<ProductReview> getProductReviews(Long id);

	public ProductReview saveProductReview(long id, ProductReview review);

}
