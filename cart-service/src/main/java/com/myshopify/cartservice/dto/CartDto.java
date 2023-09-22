package com.myshopify.cartservice.dto;

import java.util.List;

import com.myshopify.cartservice.model.CartItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	private String id;
	private List<CartItem> items;
}
