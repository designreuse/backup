
package com.nvidia.cosmos.cloud.dtos.node;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "key", "serial_number", "node_status" })
public class NodeDTO {

	@JsonProperty("key")
	@ApiModelProperty(required = true, value="Mandatory")
	private String key;
	
	@NotNull(message = "Serial Number must not be Null!")
	@NotBlank(message = "Serial Number must not be blank!")
	@NotEmpty(message = "Serial Number must not be Empty!")
	@JsonProperty("serial_number")
	@ApiModelProperty(required = true, value="Mandatory")
	private String serialNumber;
	
	@JsonProperty("node_status")
	@Valid
	private NodeStatusDTO nodeStatus;
	private Integer clusterId;
	private Integer nodeId;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The key
	 */
	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 *            The key
	 */
	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @return The serialNumber
	 */
	@JsonProperty("serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 
	 * @param serialNumber
	 *            The serial_number
	 */
	@JsonProperty("serial_number")
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the nodeStatus
	 */
	@JsonProperty("node_status")
	public NodeStatusDTO getNodeStatus() {
		return nodeStatus;
	}

	/**
	 * @param nodeStatus
	 *            the nodeStatus to set
	 */
	@JsonProperty("node_status")
	public void setNodeStatus(NodeStatusDTO nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeDTO [key=" + key + ", serialNumber=" + serialNumber + ", nodeStatus=" + nodeStatus + ", clusterId=" + clusterId + ", nodeId="
				+ nodeId + ", additionalProperties=" + additionalProperties + "]";
	}

}
