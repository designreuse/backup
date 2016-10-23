package com.nvidia.cosmos.cloud.dtos;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ProfileDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	
	private String name;
	
	


	public ProfileDTO() {
		super();
	}


	public ProfileDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		//this.email = email;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	

	
}
