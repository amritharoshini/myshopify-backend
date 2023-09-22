package com.myshopify.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopify.cartservice.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

}
