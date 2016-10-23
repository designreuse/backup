package com.nvidia.cosmos.cloud.dtos;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DockerImageRequestDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Namespace should not be null or empty")
	private String namespace;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Name should not be null or empty")
	private String name;
	
	/**
	 * @return the namespace
	 */
	public String getNamespace() {
		return namespace;
	}
	
	/**
	 * @param namespace
	 *            the namespace to set
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
