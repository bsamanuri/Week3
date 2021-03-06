package com.epam.training.reviewservice.productreviewservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.reviewservice.productreviewservice.model.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
	List<ProductReview> findByProdId(Long prodId);
	ProductReview findByIdAndProdId(Long reviewId, Long prodId);
	ProductReview deleteByIdAndProdId(Long reviewId, Long prodId);
}
