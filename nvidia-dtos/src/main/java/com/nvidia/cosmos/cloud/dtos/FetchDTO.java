package com.nvidia.cosmos.cloud.dtos;

public class FetchDTO implements BaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	public FetchDTO(String name) {
		super();
		this.name = name;
	}

	public FetchDTO() {
		super();
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
