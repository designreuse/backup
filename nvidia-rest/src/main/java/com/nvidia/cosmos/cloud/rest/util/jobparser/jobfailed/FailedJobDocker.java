
package com.nvidia.cosmos.cloud.rest.util.jobparser.jobfailed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nvidia.cosmos.cloud.dtos.job.Parameters;
import com.nvidia.cosmos.cloud.rest.util.jobparser.PortMapping;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "force_pull_image", "image", "network", "parameters", "port_mappings", "privileged" })
public class FailedJobDocker {

	@JsonProperty("force_pull_image")
	private Boolean forcePullImage;
	@JsonProperty("image")
	private String image;
	@JsonProperty("network")
	private String network;
	@JsonProperty("parameters")
	private List<Parameters> parameters = new ArrayList<Parameters>();
	@JsonProperty("port_mappings")
	private List<PortMapping> portMappings = new ArrayList<PortMapping>();
	@JsonProperty("privileged")
	private Boolean privileged;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The forcePullImage
	 */
	@JsonProperty("force_pull_image")
	public Boolean getForcePullImage() {
		return forcePullImage;
	}

	/**
	 * 
	 * @param forcePullImage
	 *            The force_pull_image
	 */
	@JsonProperty("force_pull_image")
	public void setForcePullImage(Boolean forcePullImage) {
		this.forcePullImage = forcePullImage;
	}

	/**
	 * 
	 * @return The image
	 */
	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @param image
	 *            The image
	 */
	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 
	 * @return The network
	 */
	@JsonProperty("network")
	public String getNetwork() {
		return network;
	}

	/**
	 * 
	 * @param network
	 *            The network
	 */
	@JsonProperty("network")
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * 
	 * @return The parameters
	 */
	@JsonProperty("parameters")
	public List<Parameters> getParameters() {
		return parameters;
	}

	/**
	 * 
	 * @param parameters
	 *            The parameters
	 */
	@JsonProperty("parameters")
	public void setParameters(List<Parameters> parameters) {
		this.parameters = parameters;
	}

	/**
	 * 
	 * @return The portMappings
	 */
	@JsonProperty("port_mappings")
	public List<PortMapping> getPortMappings() {
		return portMappings;
	}

	/**
	 * 
	 * @param portMappings
	 *            The port_mappings
	 */
	@JsonProperty("port_mappings")
	public void setPortMappings(List<PortMapping> portMappings) {
		this.portMappings = portMappings;
	}

	/**
	 * 
	 * @return The privileged
	 */
	@JsonProperty("privileged")
	public Boolean getPrivileged() {
		return privileged;
	}

	/**
	 * 
	 * @param privileged
	 *            The privileged
	 */
	@JsonProperty("privileged")
	public void setPrivileged(Boolean privileged) {
		this.privileged = privileged;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
