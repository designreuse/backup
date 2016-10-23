package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CustomerExistDTO implements BaseDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9._-]{4,50}$")
	private String key;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message = "Email should not be empty")
	@Size(max=128, message="Email should be maxiumum 128 characters")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Email is not valid")
	private String email;
    
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public CustomerExistDTO(String key, String email) {
		super();
		this.key = key;
		this.email = email;
	}

	public CustomerExistDTO() {
		super();
	}
	
	
}
