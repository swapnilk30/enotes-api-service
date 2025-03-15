package com.example.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	
	private Integer id;
	private String name;
	private String description;
	
	//private Boolean isActive;
	//private Boolean isDeleted;
	//private Integer createdBy;
	//private Date createtOn;
	//private Integer updatedBy;
	//private Date updatedOn;

}
