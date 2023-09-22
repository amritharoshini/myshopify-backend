package com.myshoppify.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myshoppify.productservice.dto.ProductRequest;
import com.myshoppify.productservice.dto.ProductResponse;
import com.myshoppify.productservice.dto.ProductResponse;
import com.myshoppify.productservice.model.Product;
import com.myshoppify.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.title(productRequest.getTitle())
				.category(productRequest.getCategory())
				.price(productRequest.getPrice())
				.imageUrl(productRequest.getImageUrl())
				.build();
		productRepository.save(product);
		return mapToProductResponse(product);
//		log.info("Product {} is saved", product.getId());
	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> products = this.productRepository.findAll();
		return products.stream()
				.map(product -> mapToProductResponse(product))
				.toList() ;
	}

	private ProductResponse mapToProductResponse(Product product) {
		// TODO Auto-generated method stub
		return ProductResponse.builder()
				.id(product.getId())
				.title(product.getTitle())
				.category(product.getCategory())
				.price(product.getPrice())
				.imageUrl(product.getImageUrl())
				.build();
				
	}

	public ProductResponse getProductById(String id) throws Exception {
		Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Invalid Product Id"));
		return mapToProductResponse(product);
	}

}
