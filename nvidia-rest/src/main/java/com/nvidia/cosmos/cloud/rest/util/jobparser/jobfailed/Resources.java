
package com.nvidia.cosmos.cloud.rest.util.jobparser.jobfailed;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cpus", "disk", "mem", "ports" })
public class Resources {

	@JsonProperty("cpus")
	private Double cpus;
	@JsonProperty("disk")
	private Integer disk;
	@JsonProperty("mem")
	private Integer mem;
	@JsonProperty("ports")
	private String ports;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The cpus
	 */
	@JsonProperty("cpus")
	public Double getCpus() {
		return cpus;
	}

	/**
	 * 
	 * @param cpus
	 *            The cpus
	 */
	@JsonProperty("cpus")
	public void setCpus(Double cpus) {
		this.cpus = cpus;
	}

	/**
	 * 
	 * @return The disk
	 */
	@JsonProperty("disk")
	public Integer getDisk() {
		return disk;
	}

	/**
	 * 
	 * @param disk
	 *            The disk
	 */
	@JsonProperty("disk")
	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	/**
	 * 
	 * @return The mem
	 */
	@JsonProperty("mem")
	public Integer getMem() {
		return mem;
	}

	/**
	 * 
	 * @param mem
	 *            The mem
	 */
	@JsonProperty("mem")
	public void setMem(Integer mem) {
		this.mem = mem;
	}

	/**
	 * 
	 * @return The ports
	 */
	@JsonProperty("ports")
	public String getPorts() {
		return ports;
	}

	/**
	 * 
	 * @param ports
	 *            The ports
	 */
	@JsonProperty("ports")
	public void setPorts(String ports) {
		this.ports = ports;
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
