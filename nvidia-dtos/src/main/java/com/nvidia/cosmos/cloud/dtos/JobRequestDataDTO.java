package com.nvidia.cosmos.cloud.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.dtos.job.Parameters;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class JobRequestDataDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	@Size(max=64)
	@Pattern(regexp="^[a-z]+[a-z0-9 ]*$",message="Job name should not contain special or upper-case characters and must begin with a letter")
	private String name;
	private String dockerUrl;
	private List<Parameters> dropDownValues = new ArrayList<Parameters>();
	private List<Parameters> parameters = new ArrayList<Parameters>();
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Appliance should not be null or empty")
	@NotNull
	private String serialNumber;
	private String spaceParameters;
	@AssertTrue
	private Boolean image;
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDockerUrl() {
		return dockerUrl;
	}
	public void setDockerUrl(String dockerUrl) {
		this.dockerUrl = dockerUrl;
	}
	
	public List<Parameters> getDropDownValues() {
		return dropDownValues;
	}

	public void setDropDownValues(List<Parameters> dropDownValues) {
		this.dropDownValues = dropDownValues;
	}

	public List<Parameters> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameters> parameters) {
		this.parameters = parameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpaceParameters() {
		return spaceParameters;
	}

	public void setSpaceParameters(String spaceParameters) {
		this.spaceParameters = spaceParameters;
	}
	public Boolean getImage() {
		return image;
	}

	public void setImage(Boolean image) {
		this.image = image;
	}

}
