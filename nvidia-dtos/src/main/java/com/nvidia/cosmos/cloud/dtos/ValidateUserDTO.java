package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ValidateUserDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Email should not be empty")
	@Size(max=128, message="Email should be maxiumum 128 characters")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Email is not valid")
	private String email;
	
	private String oldPwd;

	public ValidateUserDTO() {
		super();
	}

	public ValidateUserDTO(String email, String oldPwd) {
		super();
		this.email = email;
		this.oldPwd = oldPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
	

}
