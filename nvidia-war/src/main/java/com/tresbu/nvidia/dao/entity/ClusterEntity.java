package com.tresbu.nvidia.dao.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tresbu.nvidia.common.data.NvidiaData;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "clusterId", "clusterIpAddress","nfsone", "nfstwo" })
public class ClusterEntity extends NvidiaData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1516976304028223219L;

	@JsonProperty("clusterId")
	private String clusterId;

	@JsonProperty("clusterIpAddress")
	private String clusterIpAddress;

	@JsonProperty("nfsone")
	private String nfsone;
	
	@JsonProperty("nfstwo")
	private String nfstwo;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The clusterId
	 */
	@JsonProperty("clusterId")
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * 
	 * @param clusterId
	 *            The clusterId
	 */
	@JsonProperty("clusterId")
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	/**
	 * 
	 * @return The clusterIpAddress
	 */
	@JsonProperty("clusterIpAddress")
	public String getClusterIpAddress() {
		return clusterIpAddress;
	}

	/**
	 * 
	 * @param clusterIpAddress
	 *            The clusterIpAddress
	 */
	@JsonProperty("clusterIpAddress")
	public void setClusterIpAddress(String clusterIpAddress) {
		this.clusterIpAddress = clusterIpAddress;
	}
	
	/**
	 * 
	 * @return The nfsone
	 */
	@JsonProperty("nfsone")
	public String getNfsone() {
		return nfsone;
	}

	/**
	 * 
	 * @param nfsone
	 *            The nfsone
	 */
	@JsonProperty("nfsone")
	public void setNfsone(String nfsone) {
		this.nfsone = nfsone;
	}

	/**
	 * 
	 * @return The nfstwo
	 */
	@JsonProperty("nfstwo")
	public String getNfstwo() {
		return nfstwo;
	}

	/**
	 * 
	 * @param nfstwo
	 *            The nfstwo
	 */
	@JsonProperty("nfstwo")
	public void setNfstwo(String nfstwo) {
		this.nfstwo = nfstwo;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "ClusterEntity [clusterId=" + clusterId + ", clusterIpAddress=" + clusterIpAddress + ", nfsone=" + nfsone
				+ ", nfstwo=" + nfstwo + ", additionalProperties=" + additionalProperties + "]";
	}

}