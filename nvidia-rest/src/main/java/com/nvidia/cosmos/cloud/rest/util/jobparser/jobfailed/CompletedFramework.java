
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "checkpoint", "completed_executors", "executors", "failover_timeout", "hostname", "id", "name", "role", "user" })
public class CompletedFramework {

	@JsonProperty("checkpoint")
	private Boolean checkpoint;
	@JsonProperty("completed_executors")
	private List<CompletedExecutor> completedExecutors = new ArrayList<CompletedExecutor>();
	@JsonProperty("executors")
	private List<Object> executors = new ArrayList<Object>();
	@JsonProperty("failover_timeout")
	private Double failoverTimeout;
	@JsonProperty("hostname")
	private String hostname;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("role")
	private String role;
	@JsonProperty("user")
	private String user;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The checkpoint
	 */
	@JsonProperty("checkpoint")
	public Boolean getCheckpoint() {
		return checkpoint;
	}

	/**
	 * 
	 * @param checkpoint
	 *            The checkpoint
	 */
	@JsonProperty("checkpoint")
	public void setCheckpoint(Boolean checkpoint) {
		this.checkpoint = checkpoint;
	}

	/**
	 * 
	 * @return The completedExecutors
	 */
	@JsonProperty("completed_executors")
	public List<CompletedExecutor> getCompletedExecutors() {
		return completedExecutors;
	}

	/**
	 * 
	 * @param completedExecutors
	 *            The completed_executors
	 */
	@JsonProperty("completed_executors")
	public void setCompletedExecutors(List<CompletedExecutor> completedExecutors) {
		this.completedExecutors = completedExecutors;
	}

	/**
	 * 
	 * @return The executors
	 */
	@JsonProperty("executors")
	public List<Object> getExecutors() {
		return executors;
	}

	/**
	 * 
	 * @param executors
	 *            The executors
	 */
	@JsonProperty("executors")
	public void setExecutors(List<Object> executors) {
		this.executors = executors;
	}

	/**
	 * 
	 * @return The failoverTimeout
	 */
	@JsonProperty("failover_timeout")
	public Double getFailoverTimeout() {
		return failoverTimeout;
	}

	/**
	 * 
	 * @param failoverTimeout
	 *            The failover_timeout
	 */
	@JsonProperty("failover_timeout")
	public void setFailoverTimeout(Double failoverTimeout) {
		this.failoverTimeout = failoverTimeout;
	}

	/**
	 * 
	 * @return The hostname
	 */
	@JsonProperty("hostname")
	public String getHostname() {
		return hostname;
	}

	/**
	 * 
	 * @param hostname
	 *            The hostname
	 */
	@JsonProperty("hostname")
	public void setHostname(String hostname) {
		this.hostname = hostname;
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
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The role
	 */
	@JsonProperty("role")
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role
	 *            The role
	 */
	@JsonProperty("role")
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 
	 * @return The user
	 */
	@JsonProperty("user")
	public String getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 *            The user
	 */
	@JsonProperty("user")
	public void setUser(String user) {
		this.user = user;
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
