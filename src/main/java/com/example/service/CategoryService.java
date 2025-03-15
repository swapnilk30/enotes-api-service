package com.example.service;

import java.util.List;

import com.example.dto.CategoryDto;
import com.example.dto.CategoryResponse;

public interface CategoryService {
	
	Boolean saveCategory(CategoryDto categoryDto);

	List<CategoryDto> getAllCategory();

	List<CategoryResponse> getActiveCategory();
}
