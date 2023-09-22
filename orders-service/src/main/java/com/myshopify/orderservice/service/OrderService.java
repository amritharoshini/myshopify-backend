package com.myshopify.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.myshopify.orderservice.dto.InventoryResponse;
import com.myshopify.orderservice.dto.OrderLineItemsDto;
import com.myshopify.orderservice.dto.OrderRequest;
import com.myshopify.orderservice.exception.ProductNotInStockException;
import com.myshopify.orderservice.model.Order;
import com.myshopify.orderservice.model.OrderLineItems;
import com.myshopify.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;

	private final WebClient.Builder webClient;
	
	public void placeOrder(OrderRequest orderRequest) throws ProductNotInStockException {
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
				.stream()
				.map(item -> mapToOrderLineItem(item))
				.toList();
		Order order = Order.builder()
				.orderNumber(UUID.randomUUID().toString())
				.orderLineItemsList(orderLineItems)
				.build();
	
		List<String> skuCodes = order
				.getOrderLineItemsList()
				.stream()
				.map(item -> item.getSkuCode())
				.toList();
		
		InventoryResponse[] response = webClient
				.build()
				.get()
				.uri("/api/inventory", 
						 uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		 
		boolean allProductsInStock = Arrays.stream(response).allMatch(res -> res.isInStock());
		
		if(allProductsInStock)
			orderRepository.save(order);
		else 
			throw new ProductNotInStockException("Product is not in stock. Please try again later");
	}
	
	private OrderLineItems mapToOrderLineItem(OrderLineItemsDto orderLineItemDto) {
		return OrderLineItems.builder()
				.id(orderLineItemDto.getId())
				.skuCode(orderLineItemDto.getSkuCode())
				.price(orderLineItemDto.getPrice())
				.quantity(orderLineItemDto.getQuantity())
				.build();
	}
	
}
