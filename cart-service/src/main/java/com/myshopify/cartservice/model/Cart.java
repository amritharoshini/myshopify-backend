package com.myshopify.cartservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value="cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	
	@Id
	private String id;
	private List<CartItem> items;

}
