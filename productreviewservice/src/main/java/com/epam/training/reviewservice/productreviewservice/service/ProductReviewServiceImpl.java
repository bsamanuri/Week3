package com.epam.training.reviewservice.productreviewservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.reviewservice.productreviewservice.model.ProductReview;
import com.epam.training.reviewservice.productreviewservice.repository.ProductReviewRepository;



@Service
public class ProductReviewServiceImpl implements ProductReviewService {

	@Autowired
	private ProductReviewRepository productReviewRepository;

	@Override
	public List<ProductReview> getProductReviews(Long id) {
		return productReviewRepository.findByProdId(id);
	}

	/**
	 * 
	 */
	@Override
	public ProductReview saveProductReviews(ProductReview review, Long prodId) {
		review.setProdId(prodId);
		return productReviewRepository.save(review);
	}

	/**
	 * 
	 */
	@Override
	public boolean deleteProductReview(Long prodId, Long reviewId) {

		boolean isDeleted = false;
		ProductReview review = productReviewRepository.findByIdAndProdId(reviewId, prodId);
		if (review != null) {
			productReviewRepository.delete(review);
			isDeleted = true;
		}
		return isDeleted;
	}

	/**
	 * 
	 */
	@Override
	public ProductReview updateProductReview(ProductReview review, Long reviewId, Long prodId) {

		ProductReview updateReview = productReviewRepository.findByIdAndProdId(reviewId, prodId);
		if (updateReview != null) {
			updateReview.setRating(review.getRating());
			updateReview.setRemarks(review.getRemarks());
			productReviewRepository.save(updateReview);
		}
		return updateReview;
	}

}
