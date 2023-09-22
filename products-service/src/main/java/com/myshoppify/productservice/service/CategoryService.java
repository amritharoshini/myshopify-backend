package com.myshoppify.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myshoppify.productservice.dto.CategoryDto;
import com.myshoppify.productservice.model.Category;
import com.myshoppify.productservice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository repository;

	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = this.repository.findAll();
		return categories.stream()
				.map(category -> mapToCategoryDto(category))
				.toList() ;
	}

	private CategoryDto mapToCategoryDto(Category category) {
		// TODO Auto-generated method stub
		return CategoryDto.builder()
				.category(category.getCategory())
				.description(category.getDescription())
				.build();
	}

	public void createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = Category.builder()
				.category(categoryDto.getCategory())
				.description(categoryDto.getDescription())
				.build();
		repository.save(category);
	}

}
