package com.nvidia.cosmos.cloud.rest.util.jobparser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "key_id", "action_id" })
public class AlertVerify {
	@JsonProperty("key_id")
	private String keyId;
	@JsonProperty("action_id")
	private String actionId;

	/**
	 * @return the keyId
	 */
	@JsonProperty("key_id")
	public String getKeyId() {
		return keyId;
	}

	/**
	 * @param keyId
	 *            the keyId to set
	 */
	@JsonProperty("key_id")
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * @return the actionId
	 */
	@JsonProperty("action_id")
	public String getActionId() {
		return actionId;
	}

	/**
	 * @param actionId
	 *            the actionId to set
	 */
	@JsonProperty("action_id")
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

}
