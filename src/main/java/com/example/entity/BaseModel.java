package com.example.entity;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseModel {

	private Boolean isActive;
	private Boolean isDeleted;
	private Integer createdBy;
	private Date createtOn;
	private Integer updatedBy;
	private Date updatedOn;

}
