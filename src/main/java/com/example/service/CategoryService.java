package com.example.service;

import java.util.List;

import com.example.entity.Category;

public interface CategoryService {
	
	Boolean saveCategory(Category category);

	List<Category> getAllCategory();
}
