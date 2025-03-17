package com.example.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		List<Category> categories = categoryRepository.findByIsDeletedFalse();
		
		List<CategoryDto> categoryDtos = categories.stream().map(cat -> mapper.map(cat,CategoryDto.class)).toList();
		return categoryDtos;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		
		List<CategoryResponse> categoryResponses = categories.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
		return categoryResponses;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Optional<Category> findByCategoryId = categoryRepository.findByIdAndIsDeletedFalse(id);
		
		if(findByCategoryId.isPresent()) {
			Category category = findByCategoryId.get();
			CategoryDto categoryDto = mapper.map(category,CategoryDto.class);
			return categoryDto;
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Integer id) {
		Optional<Category> findCategoryById = categoryRepository.findById(id);
		if(findCategoryById.isPresent()) {
			Category category = findCategoryById.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
			return true;
		}
		
		return false;
	}

}
