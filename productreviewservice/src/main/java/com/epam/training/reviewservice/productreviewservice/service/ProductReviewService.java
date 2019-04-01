package com.epam.training.reviewservice.productreviewservice.service;

import java.util.List;
import java.util.Optional;

import com.epam.training.reviewservice.productreviewservice.model.ProductReview;

public interface ProductReviewService {

	public List<ProductReview> getProductReviews(Long id);

	public ProductReview saveProductReviews(ProductReview review, Long prodId);

	public boolean deleteProductReview(Long prodId, Long reviewId);

	public ProductReview updateProductReview(ProductReview review, Long reviewId, Long prodId);

}
