package com.epam.training.reviewservice.productreviewservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
	public class ProductReviewNotFoundException extends RuntimeException{
		
		private static final long serialVersionUID = 1L;

		public ProductReviewNotFoundException(String errMsg)
		{
			super(errMsg);
		}
		
}
