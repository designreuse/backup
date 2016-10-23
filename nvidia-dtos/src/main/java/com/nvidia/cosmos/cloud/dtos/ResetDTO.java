package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;


public class ResetDTO implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message = "Email should not be empty")
	@Size(max=128, message="Email should be maxiumum 128 characters")
	@Pattern(regexp="^[-a-zA-Z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-zA-Z0-9~!$%^&*_=+}{\\'?]+)*@([a-zA-Z0-9_][-a-zA-Z0-9_]*(\\.[-a-zA-Z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-zA-Z][a-zA-Z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$",message="Email is not valid")
	@ApiModelProperty(required = true, value="Mandatory")
	private String email;
	
	public ResetDTO(){
		
	}
	public ResetDTO(String email){
		this.email = email;
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
	
}
