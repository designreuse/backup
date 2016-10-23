package com.nvidia.cosmos.cloud.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "job_host", "container_name" })
public class ContainerStreamDTO {

	@JsonProperty("job_host")
	private String jobHost;
	@JsonProperty("container_name")
	private String containerName;

	@JsonProperty("job_host")
	public String getJobHost() {
		return jobHost;
	}

	@JsonProperty("job_host")
	public void setJobHost(String jobHost) {
		this.jobHost = jobHost;
	}

	@JsonProperty("container_name")
	public String getContainerName() {
		return containerName;
	}

	@JsonProperty("container_name")
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	
public ContainerStreamDTO() {
}
	

	@Override
	public String toString() {
		return "ContainerStream [jobHost=" + jobHost + ", containerName=" + containerName + "]";
	}

}
