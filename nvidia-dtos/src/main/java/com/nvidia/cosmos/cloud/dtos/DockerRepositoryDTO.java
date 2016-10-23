package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DockerRepositoryDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String namespace;
	private String visibility;
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	@Size(max=32)
	@Pattern(regexp="^[a-z]+[a-z0-9 ]*$",message="Repository name should not contain special character or space")
	private String repository;
	private String description;

	public DockerRepositoryDTO() {
	}
	
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
	 * @return the visibility
	 */
	public String getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            the visibility to set
	 */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the repository
	 */
	public String getRepository() {
		return repository;
	}

	/**
	 * @param repository
	 *            the repository to set
	 */
	public void setRepository(String repository) {
		this.repository = repository;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DockerRepositoryDTO [namespace=" + namespace + ", visibility=" + visibility + ", repository=" + repository + ", description="
				+ description + "]";
	}

}
