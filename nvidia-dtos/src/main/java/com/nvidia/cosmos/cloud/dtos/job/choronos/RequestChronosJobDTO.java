package com.nvidia.cosmos.cloud.dtos.job.choronos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nvidia.cosmos.cloud.dtos.job.Fetch;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestChronosJobDTO {

	@JsonProperty("schedule")
	private String schedule;

	@JsonProperty("name")
	private String name;

	@JsonProperty("retries")
	private Integer retries;

	@JsonProperty("description")
	private String description;

	@JsonProperty("ownerName")
	private String ownerName;

	@JsonProperty("uris")
	private String[] uris;

	@JsonProperty("fetch")
	private List<Fetch> fetch;

	@JsonProperty("container")
	private ChronosContainer container;

	@JsonProperty("cpus")
	private int cpus;

	@JsonProperty("mem")
	private int mem;

	@JsonProperty("command")
	private String command;

	public RequestChronosJobDTO() {
	}

	/**
	 * @return the schedule
	 */
	@JsonProperty("schedule")
	public String getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule
	 *            the schedule to set
	 */
	@JsonProperty("schedule")
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the uris
	 */
	@JsonProperty("uris")
	public String[] getUris() {
		return uris;
	}

	/**
	 * @param uris
	 *            the uris to set
	 */
	@JsonProperty("uris")
	public void setUris(String[] uris) {
		this.uris = uris;
	}

	/**
	 * @return the fetch
	 */
	@JsonProperty("fetch")
	public List<Fetch> getFetch() {
		return fetch;
	}

	/**
	 * @param fetch
	 *            the fetch to set
	 */
	@JsonProperty("fetch")
	public void setFetch(List<Fetch> fetch) {
		this.fetch = fetch;
	}

	/**
	 * @return the cpus
	 */
	@JsonProperty("cpus")
	public int getCpus() {
		return cpus;
	}

	/**
	 * @param cpus
	 *            the cpus to set
	 */
	@JsonProperty("cpus")
	public void setCpus(int cpus) {
		this.cpus = cpus;
	}

	/**
	 * @return the mem
	 */
	@JsonProperty("mem")
	public int getMem() {
		return mem;
	}

	/**
	 * @param mem
	 *            the mem to set
	 */
	@JsonProperty("mem")
	public void setMem(int mem) {
		this.mem = mem;
	}

	/**
	 * @return the command
	 */
	@JsonProperty("command")
	public String getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	@JsonProperty("command")
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @return the description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName
	 *            the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the container
	 */
	public ChronosContainer getContainer() {
		return container;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setContainer(ChronosContainer container) {
		this.container = container;
	}

	/**
	 * @return the retries
	 */
	@JsonProperty("retries")
	public Integer getRetries() {
		return retries;
	}

	/**
	 * @param retries
	 *            the retries to set
	 */
	@JsonProperty("retries")
	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	/**
	 * @param schedule
	 * @param name
	 * @param retries
	 * @param description
	 * @param ownerName
	 * @param uris
	 * @param fetch
	 * @param container
	 * @param cpus
	 * @param mem
	 * @param command
	 */
	public RequestChronosJobDTO(String schedule, String name, Integer retries, String description, String ownerName, String[] uris, List<Fetch> fetch,
			ChronosContainer container, int cpus, int mem, String command) {
		super();
		this.schedule = schedule;
		this.name = name;
		this.retries = retries;
		this.description = description;
		this.ownerName = ownerName;
		this.uris = uris;
		this.fetch = fetch;
		this.container = container;
		this.cpus = cpus;
		this.mem = mem;
		this.command = command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChronosJob [schedule=" + schedule + ", name=" + name + ", retries=" + retries + ", description=" + description + ", ownerName="
				+ ownerName + ", uris=" + Arrays.toString(uris) + ", fetch=" + fetch + ", container=" + container + ", cpus=" + cpus + ", mem=" + mem
				+ ", command=" + command + "]";
	}

}
