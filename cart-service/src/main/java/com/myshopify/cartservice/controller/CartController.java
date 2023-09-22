package com.myshopify.cartservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myshopify.cartservice.dto.CartDto;
import com.myshopify.cartservice.dto.CreateCartResponse;
import com.myshopify.cartservice.model.CartItem;
import com.myshopify.cartservice.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateCartResponse createCart() {
		return cartService.createCart();
	}
	
	@PutMapping("/{cartId}")
	@ResponseStatus(HttpStatus.OK)
	public CartDto addToCart(@PathVariable String cartId, @RequestBody CartItem cartItem) throws Exception {
		return cartService.addToCart(cartId, cartItem);
	}
	
	@GetMapping("/{cartId}")
	@ResponseStatus(HttpStatus.OK)
	public CartDto getCartById(@PathVariable String cartId) throws Exception {
		return cartService.getCart(cartId);
	}
	
	@DeleteMapping("/{cartId}")
	@ResponseStatus(HttpStatus.OK)
	public void removeCart(@PathVariable String cartId) {
		cartService.revomeCart(cartId);
	}

}
