/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * @author bprasad
 *
 */
@ApiModel
public class EntitlementDTO implements BaseDTO{
	public static final int MAX_LENGTH_NAME = 50;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Name should not be null")
	@Pattern(regexp="^[a-z0-9_]{4,50}$", message="Name must match ^[a-z0-9]{4,50}$")
	private String name;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Key should not be null")
	@Size(min=4, max=50, message="Key should should contain minimum of 4 characters")
	private String key;
	
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Serialnumber should not be null")
	@Size(min=4, max=50, message="SerialNumber should should contain minimum of 4 characters")
	private String serialNumber;
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="InflexdbUrl should not be null")
	private String influxdbUrl;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="data base name should not be null")
	@Pattern(regexp="^[a-z0-9_]{4,50}$", message="Database name must match ^[a-z0-9]{4,50}$") /*._-*/
	private String databaseName;
	
	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Inflexdbuser should not be null")
	@Size(min=4, max=50, message="Inflexdbuser should should contain minimum of 4 characters")
	private String influxdbUser;

	/**
	 * 
	 */
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Inflexdbpassword should not be null")
	@Size(min=4, max=50, message="Inflexdbpassword should should contain minimum of 4 characters")
	private String influxdbPassword;
	
	
	private CustomerDTO customer;
	

	/**
 * @return the influxdbUrl
 */
public String getInfluxdbUrl() {
	return influxdbUrl;
}
/**
 * @param influxdbUrl the influxdbUrl to set
 */
public void setInfluxdbUrl(String influxdbUrl) {
	this.influxdbUrl = influxdbUrl;
}
/**
 * @return the influxdbUser
 */
public String getInfluxdbUser() {
	return influxdbUser;
}
/**
 * @param influxdbUser the influxdbUser to set
 */
public void setInfluxdbUser(String influxdbUser) {
	this.influxdbUser = influxdbUser;
}
/**
 * @return the influxdbPassword
 */
public String getInfluxdbPassword() {
	return influxdbPassword;
}
/**
 * @param influxdbPassword the influxdbPassword to set
 */
public void setInfluxdbPassword(String influxdbPassword) {
	this.influxdbPassword = influxdbPassword;
}


public String getDatabaseName() {
	return databaseName;
}
public void setDatabaseName(String databaseName) {
	this.databaseName = databaseName;
}
	/**
	 * 
	 */
	public EntitlementDTO(){
	}
	/**
	 * @param name
	 * @param email
	 * @param customer
	 */
	public EntitlementDTO(String name, String key, String serialNumber, CustomerDTO customer) {
		super();
		this.name = name;
		this.key = key;
		this.customer = customer;
		this.serialNumber = serialNumber;
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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the email to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
}
