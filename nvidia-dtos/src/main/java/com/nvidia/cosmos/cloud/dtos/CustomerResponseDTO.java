package com.nvidia.cosmos.cloud.dtos;

public class CustomerResponseDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String email;
	
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

	public CustomerResponseDTO() {
		super();
	}

	public CustomerResponseDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

}
