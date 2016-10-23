package com.nvidia.cosmos.cloud.dtos.job.choronos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nvidia.cosmos.cloud.dtos.job.Parameters;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChronosContainer {

	@JsonProperty("type")
	private String type;

	@JsonProperty("image")
	private String image;

	@JsonProperty("network")
	private String network;

	@JsonProperty("forcePullImage")
	private Boolean forcePullImage;

	@JsonProperty("parameters")
	private List<Parameters> parameters;

	@JsonProperty("volumes")
	private List<Object> volumes = new ArrayList<Object>();

	/**
	 * @return the type
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the image
	 */
	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the network
	 */
	@JsonProperty("network")
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 *            the network to set
	 */
	@JsonProperty("network")
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the forcePullImage
	 */
	@JsonProperty("forcePullImage")
	public Boolean isForcePullImage() {
		return forcePullImage;
	}

	/**
	 * @param forcePullImage
	 *            the forcePullImage to set
	 */
	@JsonProperty("forcePullImage")
	public void setForcePullImage(Boolean forcePullImage) {
		this.forcePullImage = forcePullImage;
	}

	/**
	 * @return the parameters
	 */
	@JsonProperty("parameters")
	public List<Parameters> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	@JsonProperty("parameters")
	public void setParameters(List<Parameters> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the volumes
	 */
	public List<Object> getVolumes() {
		return volumes;
	}

	/**
	 * @param volumes
	 *            the volumes to set
	 */
	public void setVolumes(List<Object> volumes) {
		this.volumes = volumes;
	}

	public ChronosContainer() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChronosContainer [type=" + type + ", image=" + image + ", network=" + network + ", forcePullImage=" + forcePullImage + ", parameters="
				+ parameters + ", volumes=" + volumes + "]";
	}

}
