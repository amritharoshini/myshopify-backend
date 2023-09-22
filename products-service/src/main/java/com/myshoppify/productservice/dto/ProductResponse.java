package com.myshoppify.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private String id;
	private String title;
	private String category;
	private BigDecimal price;
	private String imageUrl;
}
