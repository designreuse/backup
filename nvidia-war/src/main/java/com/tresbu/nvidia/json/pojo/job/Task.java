
package com.tresbu.nvidia.json.pojo.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "host", "ipAddresses", "ports", "startedAt", "stagedAt", "version", "slaveId", "appId", "healthCheckResults" })
public class Task {

	@JsonIgnore
	private LastTaskFailure lastTaskFailure;
	@JsonIgnore
	private String status;
	@JsonIgnore
	private String image;
	@JsonIgnore
	private String network;
	@JsonProperty("id")
	private String id;
	@JsonProperty("host")
	private String host;
	@JsonProperty("ipAddresses")
	private List<Object> ipAddresses = new ArrayList<Object>();
	@JsonProperty("ports")
	private List<Integer> ports = new ArrayList<Integer>();
	@JsonProperty("startedAt")
	private String startedAt;
	@JsonProperty("stagedAt")
	private String stagedAt;
	@JsonProperty("version")
	private String version;
	@JsonProperty("slaveId")
	private String slaveId;
	@JsonProperty("appId")
	private String appId;
	@JsonIgnore
	private Date startedAtDate;
	@JsonProperty("healthCheckResults")
	private List<HealthCheckResult> healthCheckResults = new ArrayList<HealthCheckResult>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return the lastTaskFailure
	 */
	@JsonIgnore
	public LastTaskFailure getLastTaskFailure() {
		return lastTaskFailure;
	}

	/**
	 * @param lastTaskFailure
	 *            the lastTaskFailure to set
	 */
	@JsonIgnore
	public void setLastTaskFailure(LastTaskFailure lastTaskFailure) {
		this.lastTaskFailure = lastTaskFailure;
	}

	/**
	 * @return the image
	 */
	@JsonIgnore
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	@JsonIgnore
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the network
	 */
	@JsonIgnore
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 *            the network to set
	 */
	@JsonIgnore
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the status
	 */
	@JsonIgnore
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	@JsonIgnore
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The host
	 */
	@JsonProperty("host")
	public String getHost() {
		return host;
	}

	/**
	 * 
	 * @param host
	 *            The host
	 */
	@JsonProperty("host")
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 
	 * @return The ipAddresses
	 */
	@JsonProperty("ipAddresses")
	public List<Object> getIpAddresses() {
		return ipAddresses;
	}

	/**
	 * 
	 * @param ipAddresses
	 *            The ipAddresses
	 */
	@JsonProperty("ipAddresses")
	public void setIpAddresses(List<Object> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

	/**
	 * 
	 * @return The ports
	 */
	@JsonProperty("ports")
	public List<Integer> getPorts() {
		return ports;
	}

	/**
	 * 
	 * @param ports
	 *            The ports
	 */
	@JsonProperty("ports")
	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}

	/**
	 * 
	 * @return The startedAt
	 */
	@JsonProperty("startedAt")
	public String getStartedAt() {
		return startedAt;
	}

	/**
	 * 
	 * @param startedAt
	 *            The startedAt
	 */
	@JsonProperty("startedAt")
	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	/**
	 * 
	 * @return The stagedAt
	 */
	@JsonProperty("stagedAt")
	public String getStagedAt() {
		return stagedAt;
	}

	/**
	 * 
	 * @param stagedAt
	 *            The stagedAt
	 */
	@JsonProperty("stagedAt")
	public void setStagedAt(String stagedAt) {
		this.stagedAt = stagedAt;
	}

	/**
	 * 
	 * @return The version
	 */
	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version
	 *            The version
	 */
	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 
	 * @return The slaveId
	 */
	@JsonProperty("slaveId")
	public String getSlaveId() {
		return slaveId;
	}

	/**
	 * 
	 * @param slaveId
	 *            The slaveId
	 */
	@JsonProperty("slaveId")
	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	/**
	 * 
	 * @return The appId
	 */
	@JsonProperty("appId")
	public String getAppId() {
		return appId;
	}

	/**
	 * 
	 * @param appId
	 *            The appId
	 */
	@JsonProperty("appId")
	public void setAppId(String appId) {
		this.appId = appId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonIgnore
	public Date getStartedAtDate() {
		return startedAtDate;
	}

	@JsonIgnore
	public Date setStartedAtDate(Date startedAtDate) {
		return this.startedAtDate = startedAtDate;
	}

	/**
	 * @return the healthCheckResults
	 */
	@JsonProperty("healthCheckResults")
	public List<HealthCheckResult> getHealthCheckResults() {
		return healthCheckResults;
	}

	/**
	 * @param healthCheckResults
	 *            the healthCheckResults to set
	 */
	@JsonProperty("healthCheckResults")
	public void setHealthCheckResults(List<HealthCheckResult> healthCheckResults) {
		this.healthCheckResults = healthCheckResults;
	}

}
