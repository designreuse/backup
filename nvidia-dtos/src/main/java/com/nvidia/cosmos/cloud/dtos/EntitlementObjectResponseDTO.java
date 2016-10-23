/**
 * 
 */
package com.nvidia.cosmos.cloud.dtos;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class EntitlementObjectResponseDTO implements BaseDTO{
	public static final int MAX_LENGTH_NAME = 50;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private String influxdbUrl;
	
	
	private String databaseName;
	
	
	private String influxdbUser;

	
	private String influxdbPassword;
	
	
	
	

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
	public EntitlementObjectResponseDTO(){
	}
	/**
	 * @param name
	 * @param email
	 * @param customer
	 */
	public EntitlementObjectResponseDTO(String name,String influxdbUrl,String databaseName,String influxdbUser,String influxdbPassword) {
		super();
		this.name = name;
		this.influxdbUrl = influxdbUrl;
		this.databaseName = databaseName;
		this.influxdbUser = influxdbUser;
		this.influxdbPassword = influxdbPassword;
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
	
	
	
	
}
