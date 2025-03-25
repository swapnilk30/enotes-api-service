package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryDto;
import com.example.dto.CategoryResponse;
import com.example.entity.Category;
import com.example.exception.ResourceNotFoundException;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/save-category")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {

		Boolean saveCategory = categoryService.saveCategory(categoryDto);

		if (saveCategory) {

			return new ResponseEntity<>("saved Category", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Category Not Saved ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllCategory() {

		List<CategoryDto> allCategory = categoryService.getAllCategory();

		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/active")
	public ResponseEntity<?> getActiveCategory() {

		List<CategoryResponse> allCategory = categoryService.getActiveCategory();

		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception {
		CategoryDto categoryDto = categoryService.getCategoryById(id);
		if (ObjectUtils.isEmpty(categoryDto)) {
			return new ResponseEntity<>("Category Not Found with Id : " + id, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoryDto, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
		Boolean isDeleted = categoryService.deleteCategory(id);
		if (isDeleted) {
			return new ResponseEntity<>("Category Deleted SuccessFully Id ", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("CAtegory Not Deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
