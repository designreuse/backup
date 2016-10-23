
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
@JsonPropertyOrder({ "completed_tasks", "container", "directory", "id", "name", "queued_tasks", "resources", "source", "tasks" })
public class CompletedExecutor {

	@JsonProperty("completed_tasks")
	private List<CompletedTask> completedTasks = new ArrayList<CompletedTask>();
	@JsonProperty("container")
	private String container;
	@JsonProperty("directory")
	private String directory;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("queued_tasks")
	private List<Object> queuedTasks = new ArrayList<Object>();
	@JsonProperty("resources")
	private Resources resources;
	@JsonProperty("source")
	private String source;
	@JsonProperty("tasks")
	private List<Object> tasks = new ArrayList<Object>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The completedTasks
	 */
	@JsonProperty("completed_tasks")
	public List<CompletedTask> getCompletedTasks() {
		return completedTasks;
	}

	/**
	 * 
	 * @param completedTasks
	 *            The completed_tasks
	 */
	@JsonProperty("completed_tasks")
	public void setCompletedTasks(List<CompletedTask> completedTasks) {
		this.completedTasks = completedTasks;
	}

	/**
	 * 
	 * @return The container
	 */
	@JsonProperty("container")
	public String getContainer() {
		return container;
	}

	/**
	 * 
	 * @param container
	 *            The container
	 */
	@JsonProperty("container")
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * 
	 * @return The directory
	 */
	@JsonProperty("directory")
	public String getDirectory() {
		return directory;
	}

	/**
	 * 
	 * @param directory
	 *            The directory
	 */
	@JsonProperty("directory")
	public void setDirectory(String directory) {
		this.directory = directory;
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
	 * @return The queuedTasks
	 */
	@JsonProperty("queued_tasks")
	public List<Object> getQueuedTasks() {
		return queuedTasks;
	}

	/**
	 * 
	 * @param queuedTasks
	 *            The queued_tasks
	 */
	@JsonProperty("queued_tasks")
	public void setQueuedTasks(List<Object> queuedTasks) {
		this.queuedTasks = queuedTasks;
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
	 * @return The source
	 */
	@JsonProperty("source")
	public String getSource() {
		return source;
	}

	/**
	 * 
	 * @param source
	 *            The source
	 */
	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 
	 * @return The tasks
	 */
	@JsonProperty("tasks")
	public List<Object> getTasks() {
		return tasks;
	}

	/**
	 * 
	 * @param tasks
	 *            The tasks
	 */
	@JsonProperty("tasks")
	public void setTasks(List<Object> tasks) {
		this.tasks = tasks;
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
