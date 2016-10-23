
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
import com.nvidia.cosmos.cloud.dtos.job.Container;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "container", "executor_id", "framework_id", "id", "labels", "name", "resources", "slave_id", "state", "statuses" })
public class CompletedTask {

	@JsonProperty("container")
	private FailedJobContainer container;
	@JsonProperty("executor_id")
	private String executorId;
	@JsonProperty("framework_id")
	private String frameworkId;
	@JsonProperty("id")
	private String id;
	@JsonProperty("labels")
	private List<Label> labels = new ArrayList<Label>();
	@JsonProperty("name")
	private String name;
	@JsonProperty("resources")
	private Resources resources;
	@JsonProperty("slave_id")
	private String slaveId;
	@JsonProperty("state")
	private String state;
	@JsonProperty("statuses")
	private List<Object> statuses = new ArrayList<Object>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The container
	 */
	@JsonProperty("container")
	public FailedJobContainer getContainer() {
		return container;
	}

	/**
	 * 
	 * @param container
	 *            The container
	 */
	@JsonProperty("container")
	public void setContainer(FailedJobContainer container) {
		this.container = container;
	}

	/**
	 * 
	 * @return The executorId
	 */
	@JsonProperty("executor_id")
	public String getExecutorId() {
		return executorId;
	}

	/**
	 * 
	 * @param executorId
	 *            The executor_id
	 */
	@JsonProperty("executor_id")
	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}

	/**
	 * 
	 * @return The frameworkId
	 */
	@JsonProperty("framework_id")
	public String getFrameworkId() {
		return frameworkId;
	}

	/**
	 * 
	 * @param frameworkId
	 *            The framework_id
	 */
	@JsonProperty("framework_id")
	public void setFrameworkId(String frameworkId) {
		this.frameworkId = frameworkId;
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
	 * @return The labels
	 */
	@JsonProperty("labels")
	public List<Label> getLabels() {
		return labels;
	}

	/**
	 * 
	 * @param labels
	 *            The labels
	 */
	@JsonProperty("labels")
	public void setLabels(List<Label> labels) {
		this.labels = labels;
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
	 * @return The resources
	 */
	@JsonProperty("resources")
	public Resources getResources() {
		return resources;
	}

	/**
	 * 
	 * @param resources
	 *            The resources
	 */
	@JsonProperty("resources")
	public void setResources(Resources resources) {
		this.resources = resources;
	}

	/**
	 * 
	 * @return The slaveId
	 */
	@JsonProperty("slave_id")
	public String getSlaveId() {
		return slaveId;
	}

	/**
	 * 
	 * @param slaveId
	 *            The slave_id
	 */
	@JsonProperty("slave_id")
	public void setSlaveId(String slaveId) {
		this.slaveId = slaveId;
	}

	/**
	 * 
	 * @return The state
	 */
	@JsonProperty("state")
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            The state
	 */
	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return The statuses
	 */
	@JsonProperty("statuses")
	public List<Object> getStatuses() {
		return statuses;
	}

	/**
	 * 
	 * @param statuses
	 *            The statuses
	 */
	@JsonProperty("statuses")
	public void setStatuses(List<Object> statuses) {
		this.statuses = statuses;
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
