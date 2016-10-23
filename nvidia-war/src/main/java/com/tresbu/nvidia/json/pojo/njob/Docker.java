/**
 * 
 */
package com.tresbu.nvidia.json.pojo.njob;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pbatta
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Docker {

	@JsonProperty("forcePullImage")
	private String forcePullImage;

	@JsonProperty("image")
	private String image;

	@JsonProperty("parameters")
	private List<Parameters> parameters;

	@JsonProperty("portMappings")
	private List<PortDefinitions> portMappings;

	@JsonProperty("privileged")
	private String privileged;

	@JsonProperty("network")
	private String network;

	public String getForcePullImage() {
		return forcePullImage;
	}

	public void setForcePullImage(String forcePullImage) {
		this.forcePullImage = forcePullImage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the parameters
	 */
	public List<Parameters> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(List<Parameters> parameters) {
		this.parameters = parameters;
	}

	public String getPrivileged() {
		return privileged;
	}

	public void setPrivileged(String privileged) {
		this.privileged = privileged;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the portMappings
	 */
	public List<PortDefinitions> getPortMappings() {
		return portMappings;
	}

	/**
	 * @param portMappings
	 *            the portMappings to set
	 */
	public void setPortMappings(List<PortDefinitions> portMappings) {
		this.portMappings = portMappings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Docker [forcePullImage=" + forcePullImage + ", image=" + image + ", parameters=" + parameters + ", portMappings=" + portMappings
				+ ", privileged=" + privileged + ", network=" + network + "]";
	}

}