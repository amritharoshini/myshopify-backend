package com.myshoppify.productservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value="category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
	@Id
	private String category;
	private String description;
}
