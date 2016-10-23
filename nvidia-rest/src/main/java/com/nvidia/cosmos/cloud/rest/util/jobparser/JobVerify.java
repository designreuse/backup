package com.nvidia.cosmos.cloud.rest.util.jobparser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "image", "container_port" })
public class JobVerify {

	@JsonProperty("id")
	private String id;
	@JsonProperty("image")
	private String image;
	@JsonProperty("network")
	private String network;
	@JsonProperty("container_port")
	private Integer containerPort;

	/**
	 * @return the id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
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
	 * @return the containerPort
	 */
	public Integer getContainerPort() {
		return containerPort;
	}

	/**
	 * @param containerPort
	 *            the containerPort to set
	 */
	public void setContainerPort(String pContainerPort) {
		try {
			Integer intContainerPort = Integer.parseInt(pContainerPort);
			this.containerPort = intContainerPort;
		} catch (NumberFormatException e) {
			// TODO review later

		}
	}

}
