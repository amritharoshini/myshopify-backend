package com.myshoppify.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myshoppify.productservice.dto.ProductRequest;
import com.myshoppify.productservice.dto.ProductResponse;
import com.myshoppify.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
		System.out.println("Request " + productRequest);
		return this.productService.createProduct(productRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		return this.productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable("id") String id) throws Exception {
		return this.productService.getProductById(id);
	}
	
}
