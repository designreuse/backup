package com.nvidia.cosmos.cloud.dtos.job;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.nvidia.cosmos.cloud.dtos.BaseDTO;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class JobRequestDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty
	@Size(max=32)
	@Pattern(regexp="^[a-z]+[a-z0-9 ]*$",message="Job name should not contain special character")
	private String name;
	private String dockerUrl;
	private Boolean persistentService;
	private Long nodeId;
	private List<Parameters> dropDownValues = new ArrayList<Parameters>();
	private List<Parameters> parameters = new ArrayList<Parameters>();

	private String argument;
	@ApiModelProperty(required = true, value="Mandatory")
	@NotEmpty(message="Appliance should not be null or empty")
	@NotNull
	private String serialNumber;
	private Integer clusterId;
	private String spaceParameters;
	private Boolean image;

	public JobRequestDTO() {
	}

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

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPersistentService() {
		return persistentService;
	}

	public void setPersistentService(Boolean persistentService) {
		this.persistentService = persistentService;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobRequestDTO [name=" + name + ", dockerUrl=" + dockerUrl + ", persistentService=" + persistentService + ", nodeId=" + nodeId
				+ ", dropDownValues=" + dropDownValues + ", parameters=" + parameters + ", argument=" + argument + ", serialNumber=" + serialNumber
				+ ", clusterId=" + clusterId + ", spaceParameters=" + spaceParameters + ", image=" + image + "]";
	}

}
