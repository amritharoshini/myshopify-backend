package com.myshopify.orderservice.exception;

public class ProductNotInStockException extends Exception {
	public ProductNotInStockException(String message) {
		super(message);
	}
}	
