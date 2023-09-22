package com.myshopify.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myshopify.inventoryservice.dto.InventoryResponse;
import com.myshopify.inventoryservice.model.Inventory;
import com.myshopify.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;

	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode)
				.stream()
				.map(inventory -> mapToInventoryResponse(inventory))
				.toList();
	}

	private InventoryResponse mapToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity() > 0)
				.build();
	}
}
