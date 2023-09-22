package com.myshopify.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.myshopify.inventoryservice.model.Inventory;
import com.myshopify.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRespository) {
		return args -> {
			Inventory inventory1 = Inventory.builder()
					.skuCode("iphone_13")
					.quantity(100)
					.build();
			
			Inventory inventory2 = Inventory.builder()
					.skuCode("iphone_13_red")
					.quantity(0)
					.build();
			
			inventoryRespository.save(inventory1);
			inventoryRespository.save(inventory2);
		};
		
	}

}
