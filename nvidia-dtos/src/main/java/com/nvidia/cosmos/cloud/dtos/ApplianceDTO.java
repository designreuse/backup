/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author bprasad
 *
 */
@ApiModel
public class ApplianceDTO implements BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	private String serviceKey;
	
	/**
	 * 
	 */
	private CustomerDTO customer;
	/**
	 * 
	 */
	public ApplianceDTO(){
	}
	
	public ApplianceDTO(String name, String serviceKey){
		this.name = name;
		this.serviceKey = serviceKey;
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
	 * @return the serviceKey
	 */
	public String getServiceKey() {
		return serviceKey;
	}

	/**
	 * @param serviceKey the serviceKey to set
	 */
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	/**
	 * @return the customer
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplianceDTO [id=" + id + ", name=" + name + ", serviceKey=" + serviceKey + ", customer=" + customer
				+ "]";
	}

	
}
