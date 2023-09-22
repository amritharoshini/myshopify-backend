package com.myshopify.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myshopify.orderservice.dto.ErrorMessage;

@RestControllerAdvice
public class OrderExceptionHandler {

	@ExceptionHandler(ProductNotInStockException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorMessage> handleProductNotInStockException(ProductNotInStockException e){
		return ResponseEntity
				.badRequest()
				.body(ErrorMessage.builder()
						.statusCode(HttpStatus.BAD_REQUEST.value())
						.errorMessage(e.getMessage())
						.build());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> HandleException(Exception e){
		return ResponseEntity
				.badRequest()
				.body(ErrorMessage.builder()
						.statusCode(HttpStatus.BAD_REQUEST.value())
						.errorMessage(e.getMessage())
						.build());
	}
	
}
