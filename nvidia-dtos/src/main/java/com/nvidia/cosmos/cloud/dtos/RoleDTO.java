/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author bprasad
 *
 */
@ApiModel
public class RoleDTO implements BaseDTO{
	public static final int MAX_LENGTH_NAME = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
    @Length(max = MAX_LENGTH_NAME)
	private String name;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@Email
	@NotEmpty
	private String description;
	/**
	 * 
	 */
	public RoleDTO(){
	}
	
	public RoleDTO(String name, String description){
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
}
