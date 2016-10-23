/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author bprasad
 *
 */
@ApiModel
public class JWTAuthDTO implements BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	private String name;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	private String password;
	
	/**
	 * 
	 */
	public JWTAuthDTO(){
	}
	
	public JWTAuthDTO(String name, String password){
		this.name = name;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
