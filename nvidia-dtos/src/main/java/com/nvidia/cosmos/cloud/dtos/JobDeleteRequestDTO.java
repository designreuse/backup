package com.nvidia.cosmos.cloud.dtos;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;


public class JobDeleteRequestDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Name should not be null or empty")
	private String appId;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Uniqueid should not be null or empty")
	private String uniquename;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="JobApiType should not be null or empty")
	private String jobApiType;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Appliance should not be null or empty")
	private String serialNumber;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getUniquename() {
		return uniquename;
	}
	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}
	public String getJobApiType() {
		return jobApiType;
	}
	public void setJobApiType(String jobApiType) {
		this.jobApiType = jobApiType;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}
