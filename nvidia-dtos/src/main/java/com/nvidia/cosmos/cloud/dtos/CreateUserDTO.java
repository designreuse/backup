/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


@ApiModel
public class CreateUserDTO implements BaseDTO{
	
	private static final long serialVersionUID = 1L;

	
	
	
	private String name;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message = "Email should not be empty")
	@Size(max=128, message="Email should be maxiumum 128 characters")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Email is not valid")
	private String email;
	
	
	
	private String uId;
	private String gId;
	
	public CreateUserDTO(){
		super();
	}
	
	public CreateUserDTO(String name, String email){
		super();
		this.name = name;
		this.email = email;
		
	}


	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	
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

	

	/**
	 * @return the uId
	 */
	public String getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	public void setuId(String uId) {
		this.uId = uId;
	}

	/**
	 * @return the gId
	 */
	public String getgId() {
		return gId;
	}

	/**
	 * @param gId the gId to set
	 */
	public void setgId(String gId) {
		this.gId = gId;
	}

	public void setCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		
	}
	
	
}
