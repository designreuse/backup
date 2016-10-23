package com.nvidia.cosmos.cloud.rest.util.jobparser;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "command", "ip", "retval", "status" })
public class WSServerJobResponse {

	@JsonProperty("command")
	private String command;
	@JsonProperty("ip")
	private String ip;
	@JsonProperty("retval")
	private String retval;
	@JsonProperty("status")
	private String status;
	@JsonProperty("waiting")
	private String waiting;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The command
	 */
	@JsonProperty("command")
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            The command
	 */
	@JsonProperty("command")
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The ip
	 */
	@JsonProperty("ip")
	public String getIp() {
		return ip;
	}

	/**
	 * 
	 * @param ip
	 *            The ip
	 */
	@JsonProperty("ip")
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @return The retval
	 */
	@JsonProperty("retval")
	public String getRetval() {
		return retval;
	}

	/**
	 * 
	 * @param retval
	 *            The retval
	 */
	@JsonProperty("retval")
	public void setRetval(String retval) {
		this.retval = retval;
	}

	/**
	 * 
	 * @return The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the waiting
	 */
	public String getWaiting() {
		return waiting;
	}

	/**
	 * @param waiting
	 *            the waiting to set
	 */
	public void setWaiting(String waiting) {
		this.waiting = waiting;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WSServerJobResponse [command=" + command + ", ip=" + ip + ", retval=" + retval + ", status=" + status + ", waiting=" + waiting
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}
