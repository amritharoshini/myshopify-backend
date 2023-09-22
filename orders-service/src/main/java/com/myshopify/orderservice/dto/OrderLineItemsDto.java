package com.myshopify.orderservice.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemsDto {
	private Long id;
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
