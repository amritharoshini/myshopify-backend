package com.myshopify.cartservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopify.cartservice.dto.CartDto;
import com.myshopify.cartservice.dto.CreateCartResponse;
import com.myshopify.cartservice.model.Cart;
import com.myshopify.cartservice.model.CartItem;
import com.myshopify.cartservice.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository cartRepository;

	public CreateCartResponse createCart() {
		Cart cart = new Cart();
		cart.setItems(new ArrayList<CartItem>());
		cartRepository.save(cart);
		return CreateCartResponse
				.builder()
				.cartId(cart.getId())
				.build();
	}

	public CartDto addToCart(String cartId, CartItem cartItem) throws Exception {
		Cart cart = getCartById(cartId);
		
		List<CartItem> items = cart.getItems();
		items.add(cartItem);
		cart.setItems(items);
		
		return mapToCartDto(cart);
		
	}
	
	private Cart getCartById(String cartId) throws Exception {
		return cartRepository.findById(cartId)
				.orElseThrow(() -> new Exception("Invalid cart id"));
	}
	
	public CartDto getCart(String cartId) throws Exception {
		Cart cart = getCartById(cartId);
		return mapToCartDto(cart);
	}
	
	private CartDto mapToCartDto(Cart cart) {
		return CartDto
				.builder()
				.id(cart.getId())
				.items(cart.getItems())
				.build();
	}
	
	public void revomeCart(String cartId) {
		cartRepository.deleteById(cartId);
	}
	
}
