package com.example.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.dto.CategoryDto;
import com.example.dto.CategoryResponse;
import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		//dto to entity
		
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setIsActive(categoryDto.getIsActive());
		
		category.setIsDeleted(false);
		
		category.setCreatedBy(1);
		
		category.setCreatetOn(new Date());
		
		Category saveCategory = categoryRepository.save(category);
		
		
		if(ObjectUtils.isEmpty(saveCategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map(cat -> mapper.map(cat,CategoryDto.class)).toList();
		return categoryDtos;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveTrue();
		
		List<CategoryResponse> categoryResponses = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
		return categoryResponses;
	}

}
