package com.myshoppify.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myshoppify.productservice.dto.CategoryDto;
import com.myshoppify.productservice.service.CategoryService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryDto> getAllCategories(){
		return this.categoryService.getAllCategories();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCategory(@RequestBody List<CategoryDto> categories) {
		categories
		.stream()
		.forEach(category -> this.categoryService.createCategory(category));
	}
	
}
