package com.tresbu.nvidia.json.pojo.njob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tresbu.nvidia.json.pojo.job.Labels;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PortDefinitions {
	public PortDefinitions() {

	}

	@JsonProperty("port")
	private int port;

	@JsonProperty("protocol")
	private String protocol;

	@JsonProperty("labels")
	private Labels labels;

	@JsonProperty("containerPort")
	private int containerPort;

	@JsonProperty("hostPort")
	private int hostPort;

	@JsonProperty("servicePort")
	private int servicePort;

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 *            the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the labels
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * @param labels
	 *            the labels to set
	 */
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	/**
	 * @return the containerPort
	 */
	public int getContainerPort() {
		return containerPort;
	}

	/**
	 * @param containerPort
	 *            the containerPort to set
	 */
	public void setContainerPort(int containerPort) {
		this.containerPort = containerPort;
	}

	/**
	 * @return the hostPort
	 */
	public int getHostPort() {
		return hostPort;
	}

	/**
	 * @param hostPort
	 *            the hostPort to set
	 */
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	/**
	 * @return the servicePort
	 */
	public int getServicePort() {
		return servicePort;
	}

	/**
	 * @param servicePort
	 *            the servicePort to set
	 */
	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PortDefinitions [port=" + port + ", protocol=" + protocol + ", labels=" + labels + ", containerPort=" + containerPort + ", hostPort="
				+ hostPort + ", servicePort=" + servicePort + "]";
	}

}
